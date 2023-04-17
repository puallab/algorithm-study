import java.util.*;
import java.io.*;

public class KCM_Travel{
    static class Tuple implements Comparable<Tuple>{
        int to, cost, dist;
        public Tuple(int t, int c, int d){
            to = t;
            cost = c;
            dist =d;
        }

        @Override
        public int compareTo(Tuple o){
            return this.dist - o.dist;
        }

    }
    static int n, m, k;
    static ArrayList<Tuple>[] list;
    static boolean[][] vis;
    static int[][] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            st  = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            list = new ArrayList[n+1];
            vis = new boolean[n+1][m+1];
            dist = new int[n+1][m+1];
            for(int i =1; i<n+1; i++){
                list[i] = new ArrayList<>();
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            
            for(int i =0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                
                list[u].add(new Tuple(v, c, d));
            }

            int ans = dijkstar();
            if(ans == Integer.MAX_VALUE) sb.append("Poor KCM");
            else sb.append(ans);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static int dijkstar(){
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        pq.add(new Tuple(1, 0, 0));
        int ans = Integer.MAX_VALUE;
        while(!pq.isEmpty()){

            Tuple now = pq.poll();
            if(vis[now.to][now.cost]) continue;
            vis[now.to][now.cost] = true;
            dist[now.to][now.cost] = now.dist;
            if(now.to == n){
                ans = Math.min(ans, now.dist);
            }

            for(Tuple next :  list[now.to]){

                //코스트 초과면 패스
                if(next.cost + now.cost > m) continue;
                if(next.dist + now.dist >= dist[next.to][now.cost+next.cost]) continue;
                dist[next.to][now.cost+next.cost] = next.dist + now.dist;
                pq.add(new Tuple(next.to, next.cost+now.cost, next.dist + now.dist));

            }

        }
        return ans;
    }
}