import java.util.*;
import java.io.*;

public class 백조의_호수{
    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y = yy;
            x = xx;
        }
    }
    static int r, c, idx;
    static int[][] board;
    static boolean[][] vis;
    static int[] parents;
    static List<Pair> list = new ArrayList<>();
    static Queue<Pair> queue = new LinkedList<>();
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r=  Integer.parseInt(st.nextToken());
        c= Integer.parseInt(st.nextToken());
        board = new int[r][c];
        vis = new boolean[r][c];
        for(int i =0; i<r; i++){
            String s = br.readLine(); 
            for(int j=0; j<c; j++){
                if(s.charAt(j) =='X'){
                    board[i][j] = -1;
                }
                if(s.charAt(j) == 'L'){
                    list.add(new Pair(i,j));
                }
            }
        }

        
        for(int i =0; i<r; i++){
            for(int j=0; j<c; j++){
                if(vis[i][j]) continue;
                if(board[i][j] == -1) continue;
                idx++;
                bfs(i,j, idx);
            }
        }

        parents = new int[idx+1];
        for(int i =0; i<=idx; i++){
            parents[i] = i;
        }

        int ans = 0;
        while(!isFinished()){
            flow();
            ans++;
        }
        System.out.println(ans);
    }

    static void bfs(int y, int x, int idx){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        vis[y][x] = true;
        board[y][x] = idx;

        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i =0; i<4; i++){
                y = now.y + dy[i];
                x = now.x + dx[i];
                if(!isValid(y, x) || vis[y][x] ) continue;
    
                if(board[y][x] == -1){
                    queue.add(new Pair(y,x));
                }else{
                    q.add(new Pair(y, x));
                }
                vis[y][x] = true;
                board[y][x] = idx;
                
            }
        }
    }

    static void flow(){
        int qsize= queue.size();
        
        // 연결확인
        while(qsize -- > 0){
            Pair now = queue.poll();
            int index = board[now.y][now.x];
            for(int i =0; i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];
                if(!isValid(y, x) ) continue;
                if(board[y][x] > 0 && board[y][x] != index){
                    union(index, board[y][x]);
                }
                
            }
            queue.add(now);
        }

        // 물웅덩이 확장
        qsize = queue.size();
        while(qsize -- > 0){
            Pair now = queue.poll();
            int index = board[now.y][now.x];
            for(int i =0; i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];
                if(!isValid(y, x) || vis[y][x]) continue;
                if(board[y][x] == -1){
                    board[y][x] = index;
                    vis[y][x] = true;
                    queue.add(new Pair(y, x));
                }
            }
        }
    }

    static boolean isValid(int y, int x){
        return (y >= 0 && x >= 0 && y< r && x <c);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb ) return;
        parents[pb] = pa;
    }

    static int find(int a){
        if(parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    static boolean isFinished(){
        Pair A = list.get(0);
        Pair B = list.get(1);
        
        return find(board[A.y][A.x])==find(board[B.y][B.x]);
    }
    
}