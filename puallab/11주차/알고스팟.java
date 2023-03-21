import java.io.*;
import java.util.*;

public class 알고스팟{
    static class Tuple{
        int y, x, cnt;
        public Tuple(int yy, int xx, int cc){
            y =yy;
            x =xx;
            cnt =cc;
        }
    }
    static int n, m;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] dist;
    static boolean[][] vis;
    static String[] board;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        dist = new int[n][m];
        board = new String[n];
        vis = new boolean[n][m];
        for(int i =0; i<n; i++){
            board[i] = br.readLine();
        }

        PriorityQueue<Tuple> pq = new PriorityQueue<>((o1, o2) ->{
            return o1.cnt-o2.cnt;
        });
        pq.add(new Tuple(0, 0, 0));
        dist[0][0] = 0;

        while(!pq.isEmpty()){

            Tuple now = pq.poll();
            if(now.y == n-1 && now.x == m-1){
                System.out.println(dist[now.y][now.x]);
                return;
            }
            for(int i =0 ;i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];
                if(!isValid(y, x)) continue;
                if(vis[y][x]){
                    if(board[y].charAt(x) == '0' &&  dist[y][x] <= now.cnt) continue;
                    if(board[y].charAt(x) == '1' &&  dist[y][x] <= now.cnt+1) continue;
                }
                vis[y][x] =true;
                dist[y][x] = now.cnt+ board[y].charAt(x)-'0';
                pq.add(new Tuple(y, x, dist[y][x]));
                
            }

        }

    }

    static boolean isValid(int y, int x){
        return (y >=0 && x >=0 && y<n && x<m);
    }
}