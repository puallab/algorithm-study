package Week11_다익스트라;

import java.io.*;
import java.util.*;

public class BOJ_5719_거의_최단_경로 {
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

    static final int INF = Integer.MAX_VALUE;
    static int N, M, S, D, dist[], path[][];
    static boolean[][] flag;
    static ArrayList<Node> adjList[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0)
                break;

            dist = new int[N];
            path = new int[N][N];
            flag = new boolean[N][N];
            adjList = new ArrayList[N];
            for (int i = 0; i < N; i++)
                adjList[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                adjList[U].add(new Node(V, P));
            }

            dijkstra();
            findPath();
            dijkstra();
            sb.append(dist[D] == INF ? -1 : dist[D]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(S, 0));
        dist[S] = 0;

        Node now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            if (now.c > dist[now.v])
                continue;

            for (Node next : adjList[now.v]) {
                int cost = now.c + next.c;
                if (flag[now.v][next.v] || cost > dist[next.v])
                    continue;
                else if (cost < dist[next.v])
                    pq.offer(new Node(next.v, cost));

                dist[next.v] = cost;
                path[now.v][next.v] = cost;
            }
        }
    }

    private static void findPath() {
        boolean[] visit = new boolean[N];
        Queue<Integer> q = new LinkedList<>();
        q.offer(D);

        int now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now == S || visit[now])
                continue;

            visit[now] = true;
            for (int i = 0; i < N; i++) {
                if (flag[i][now] || i == now || path[i][now] != dist[now])
                    continue;

                flag[i][now] = true;
                q.offer(i);
            }
        }
    }
}