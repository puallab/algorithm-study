import java.io.*;
import java.util.*;

public class BOJ_1162_도로포장_NEW {
    static class Node implements Comparable<Node> {
        int v, cnt;
        long cost;

        public Node(int v, long cost, int cnt) {
            this.v = v;
            this.cost = cost;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost == o.cost ? this.cnt - o.cnt : (int) (this.cost - o.cost);
        }
    }

    static int N, M, K, map[][];
    static long dist[][];
    static final long INF = Long.MAX_VALUE;
    static ArrayList<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new long[K + 1][N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            adjList[i] = new ArrayList<>();

        for (int i = 0; i <= K; i++)
            Arrays.fill(dist[i], INF);


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c, 0));
            adjList[b].add(new Node(a, c, 0));
        }

        dijkstra(1);
        long min = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++)
            min = Math.min(min, dist[i][N]);
        System.out.println(min);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));
        dist[0][start] = 0;

        Node now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            if (now.cost > dist[now.cnt][now.v])
                continue;

            for (Node next : adjList[now.v]) {
                if (now.cost > dist[now.cnt][next.v])
                    continue;

                long sum = now.cost + next.cost;
                if (sum < dist[now.cnt][next.v]) {
                    pq.offer(new Node(next.v, sum, now.cnt));
                    dist[now.cnt][next.v] = sum;
                }

                if (now.cnt == K || dist[now.cnt + 1][next.v] <= now.cost)
                    continue;
                pq.offer(new Node(next.v, now.cost, now.cnt + 1));
                dist[now.cnt + 1][next.v] = now.cost;
            }
        }
    }
}