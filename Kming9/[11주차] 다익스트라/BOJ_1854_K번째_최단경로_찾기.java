package Week11_다익스트라;

import java.io.*;
import java.util.*;

public class BOJ_1854_K번째_최단경로_찾기 {
    static class Node implements Comparable<Node> {
        int v, c;

        public Node(int v, int c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }

    static int n, m, k, dist[], count[];
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        count = new int[n + 1];
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            adjList[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c));
        }

        dijkstra(1);
        for (int i = 1; i <= n; i++)
            sb.append(count[i] < k ? -1 : dist[i]).append("\n");

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        Node now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            if (count[now.v] == k)
                continue;

            dist[now.v] = now.c;
            count[now.v]++;

            for (Node next : adjList[now.v]) {
                if (count[next.v] == k)
                    continue;

                pq.offer(new Node(next.v, now.c + next.c));
            }
        }
    }
}