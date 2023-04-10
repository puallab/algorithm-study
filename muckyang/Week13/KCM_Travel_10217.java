package com.mrcAlgo.Week13_오마카세;

import java.util.*;
import java.io.*;

public class KCM_Travel_10217 {

    private static class Point implements Comparable<Point> {
        int to, cost, dist;

        public Point(int to, int cost, int dist) {
            this.to = to;
            this.cost = cost;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            return this.dist - o.dist;
        }
    }

    static int T, N, M, K;
    static int[][] dpDistCost;
    static ArrayList<Point>[] edgeList;
    static final String FAIL_STRING = "Poor KCM";
    static int minRes;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            dpDistCost = new int[N][M + 1];
            edgeList = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dpDistCost[i], Integer.MAX_VALUE);
                edgeList[i] = new ArrayList<>();
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                edgeList[u].add(new Point(v, c, d));
            }

            dpDistCost[0][0] = 0;
            minRes = Integer.MAX_VALUE;
            solve();

            sb.append(minRes == Integer.MAX_VALUE ? FAIL_STRING : minRes).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, 0));
        while (!pq.isEmpty()) {

            Point p = pq.poll();

            if (p.to == N - 1) {
                minRes = p.dist;
                return;
            }

            for (Point next : edgeList[p.to]) {
                int nextDist = p.dist + next.dist;
                int nextCost = p.cost + next.cost;
                if (M < nextCost || dpDistCost[next.to][nextCost] <= nextDist )
                    continue;
                dpDistCost[next.to][nextCost] = nextDist;
                pq.add(new Point(next.to, nextCost, nextDist));
            }
        }
    }
}
