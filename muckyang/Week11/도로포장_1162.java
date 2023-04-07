package com.mrcAlgo.Week11_다익스트라;
import java.io.*;
import java.util.*;

public class 도로포장_1162 {
    private static class Point implements Comparable<Point> {
        int to, cnt;
        long dist;

        public Point(int to, long dist, int cnt) {
            this.to = to;
            this.dist = dist;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            if (this.dist > o.dist) return 1;
            return -1;
        }
    }

    static int N, M, K;
    static ArrayList<Point>[] list;
    static long[][] visit;
    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];
        visit = new long[N][K + 1];

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
            Arrays.fill(visit[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken()) - 1;
            int V = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());
            list[U].add(new Point(V, dist, 0));
            list[V].add(new Point(U, dist, 0));
        }
        pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, K));

        Arrays.fill(visit[0], 0);
        solve();
        long minRes = Long.MAX_VALUE;
        for (int i = 0; i < K; i++) {
            minRes = Math.min(minRes, visit[N - 1][i]);
        }
        System.out.println(minRes);
    }

    private static void solve() {
        while (!pq.isEmpty()) {
            Point p = pq.poll();

            if (p.dist > visit[p.to][p.cnt])
                continue;

            for (Point next : list[p.to]) {
                long sum =next.dist + p.dist;
                if (visit[next.to][p.cnt] > sum) {
                    visit[next.to][p.cnt] = sum;
                    pq.add(new Point(next.to, visit[next.to][p.cnt], p.cnt));
                }
                if (p.cnt > 0 && visit[next.to][p.cnt - 1] > p.dist) {
                    visit[next.to][p.cnt - 1] = p.dist;
                    pq.add(new Point(next.to, visit[next.to][p.cnt - 1], p.cnt - 1));
                }
            }
        }
    }
}