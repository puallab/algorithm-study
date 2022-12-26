import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main{
    static int n, m, firstPick, secondPick, ans = Integer.MAX_VALUE;
    static int[][] dist;
    static ArrayList<Integer>[] list;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n+1][n+1];
        list = new ArrayList[n+1];
        for(int i =1; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
            dist[a][b] = dist[b][a] = 1;
        }

        //모든 a -> b의 거리 구하기
        getAllDist();

        //2개씩 뽑아 최단 거리 구하기
        getMinDist();

        System.out.println(firstPick + " " +secondPick + " " + ans*2);

    }

    static void getAllDist(){
        for(int i =1; i<=n; i++){
            bfs(i);
        }
    }

    static void bfs(int idx){
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n+1];
        vis[idx] = true;
        q.add(idx);
        while(!q.isEmpty()){
            int k = q.poll();
            int d= dist[idx][k];
            for(int next : list[k]){
                if(vis[next]) continue;
                vis[next] =true;
                dist[idx][next] = d+1;
                q.add(next);
            }
        }
    }

    static void getMinDist(){
        for(int i =1; i<n+1; i++){
            for(int j =i+1; j<n+1; j++){
                //i, j로부터 각 거리까지의 최단 거리를 구함
                int val =0;
                for(int dest =1; dest< n+1; dest++){
                    val += Math.min(dist[i][dest], dist[j][dest]);
                }

                if(ans > val){
                    firstPick = i;
                    secondPick = j;
                    ans = val;
                }
            }
        }

    }
}