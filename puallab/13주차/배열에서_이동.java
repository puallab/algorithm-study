import java.util.*;
import java.io.*;

public class 배열에서_이동{
    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }
    }
    static int n, MIN = Integer.MAX_VALUE, MAX;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] board;
    static boolean[][] vis;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        vis = new boolean[n][n];
        for(int i =0 ;i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                MIN = Math.min(MIN, board[i][j]);
                MAX = Math.max(MAX, board[i][j]);
            }
        }

        int left = 0, right = MAX-MIN;
        int ans =0;
        while(left <= right){
            int mid = (left+right)/2;
           
            if(bfs(mid)){
                ans = mid;
                right = mid -1;
            }else{
                left = mid +1;
            }
        }

        System.out.println(ans);

    }

    static boolean bfs(int gap){
        for (int min = MIN; min <= MAX - gap; min++) {
            if(!isRange(board[0][0], min, min+gap)) continue;
            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(0, 0));
            for (int i = 0; i < n; i++) {
                Arrays.fill(vis[i], false);
            }
            vis[0][0] = true;
            while (!q.isEmpty()) {
                Pair now = q.poll();
                for (int i = 0; i < 4; i++) {
                    int y = now.y + dy[i];
                    int x = now.x + dx[i];
                    if (!isValid(y, x) || vis[y][x])
                        continue;
                    if(!isRange(board[y][x], min, min+gap)) continue;
                    if (y == n - 1 && x == n - 1)
                        return true;
                   
                    vis[y][x] = true;
                    q.add(new Pair(y, x));
                }
            }
        }
        return false;        
    }

    static boolean isValid(int y, int x){
        return (y>=0 && x >=0 && y<n && x<n);
    }

    static boolean isRange(int val, int min, int max){
       return (val >= min && val <= max);
    }
}
