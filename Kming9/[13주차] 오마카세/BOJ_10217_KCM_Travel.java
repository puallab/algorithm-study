package Week12_오마카세;

import java.io.*;
import java.util.*;

public class BOJ_10217_KCM_Travel {
    static class Node implements Comparable<Node> {
        int v, c, d;

        public Node(int v, int c, int d) {
            this.v = v;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N, M, K, dist[][];
    static ArrayList<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            dist = new int[M + 1][N + 1];
            adjList = new ArrayList[N + 1];

            for (int j = 1; j <= N; j++)
                adjList[j] = new ArrayList<>();

            for (int j = 1; j <= M; j++)
                Arrays.fill(dist[j], INF);

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken()); // M 이하
                int d = Integer.parseInt(st.nextToken()); // 가장 짧은 경로

                adjList[u].add(new Node(v, c, d));
            }

            dijkstra(1);

            int min = INF;
            for (int j = 1; j <= M; j++)
                min = Math.min(min, dist[j][N]);

            sb.append(min == INF ? "Poor KCM" : min).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));
        dist[0][start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.c][now.v] < now.d)
                continue;

            for (Node next : adjList[now.v]) {
                int tc = now.c + next.c;
                int td = now.d + next.d;

                if (tc > M || dist[tc][next.v] <= td)
                    continue;

                dist[tc][next.v] = td;
                pq.offer(new Node(next.v, now.c + next.c, now.d + next.d));
            }
        }
    }
}