import java.util.*;
import java.io.*;

public class k번째_최단경로_찾기{

    static class Node{
        int to, val;
        public Node(int t, int v){
            to =t;
            val =v;
        }
    }

    static int n, m, k;
    static int[] dist, vis;    
    static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dist = new int[n+1];
        vis = new int[n+1];
        list = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from  =Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            list[from].add(new Node(to, val));
        }

        dijkstar();

        for(int i =1; i<=n; i++){
            if(vis[i] == k){
                sb.append(dist[i]);
            }else{
                sb.append(-1);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dijkstar(){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.val-o2.val );
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(vis[now.to] >= k) continue;
            vis[now.to] += 1;
            dist[now.to] = now.val;

            for(Node next : list[now.to]){
                pq.add(new Node(next.to, dist[now.to] + next.val));
            }
        }

    }
}