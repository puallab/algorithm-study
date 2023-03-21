import java.util.*;
import java.io.*;

public class 문명{

    static class Pair{
        int y, x, t;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }
    }

	static int n, k;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int[] parents;
	static int[] cnts;
	static int[][] board; 
	static Queue<Pair> q = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        parents = new int[k+1];
        cnts = new int[k+1];
        for(int i =1; i<=k; i++){
            parents[i] = i;
            cnts[i] = 1;
        }
        for(int i =0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y=  Integer.parseInt(st.nextToken())-1;
            
            q.add(new Pair(y, x));
            board[y][x] = i+1;
        }

        for(int i =0; i<k; i++){
            Pair n = q.poll();

            for(int d =0; d<4; d++){
                int y = n.y + dy[d];
                int x = n.x + dx[d];
                if(!isValid(y, x) || board[y][x] == 0) continue;

                union(board[y][x], board[n.y][n.x]);
            }
            q.add(n);
        }
        int ans = 0;
        if(cnts[1] == k) System.out.println(ans);
        else    System.out.println(bfs());
	}

    static int bfs(){
        int time = 0;
        while(!q.isEmpty()){
            int size = q.size();
            //문명 연결
            for(int i =0; i<size; i++){
                Pair now = q.poll();
                for(int d = 0; d<4; d++){
                    int y = now.y + dy[d];
                    int x = now.x + dx[d];
                    if(!isValid(y, x) || board[y][x] == 0 || board[y][x] == board[now.y][now.x]) continue;
                    
                    union(board[y][x], board[now.y][now.x]);
                    if(cnts[find(board[y][x])] == k){
                        return time;
                    }

                }
                q.add(now);
            }

            for(int i =0; i<size; i++){
                Pair now = q.poll();
                for(int d = 0; d<4; d++){
                    int y = now.y + dy[d];
                    int x = now.x + dx[d];
                    if(!isValid(y, x) || board[y][x] != 0 ) continue;
                    
                    board[y][x] = board[now.y][now.x];
                    q.add(new Pair(y, x));
                }
            }

            time++;
        }

        return time;
    }

    static int find(int idx){
        if(parents[idx] == idx){
            return idx;
        }
        return parents[idx] = find(parents[idx]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return;
        parents[pb] = pa;
        cnts[pa] += cnts[pb];
    }

	static boolean isValid(int y, int x){
		return (y>=0 && x>=0 && y<n && x<n);
	}

}