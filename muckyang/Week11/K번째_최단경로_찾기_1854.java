package com.mrcAlgo.Week11_다익스트라;
import java.io.*;
import java.util.*;

public class K번째_최단경로_찾기_1854 {

    private static class Point implements Comparable<Point> {
        int to;
        long dist;

        public Point(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            if (this.dist > o.dist)
                return 1;
            return -1;
        }

    }

    static int N, M, K;
    static long[] distance;
    static int[] checkCnt;
    static ArrayList<Point>[] verList;

    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        verList = new ArrayList[N];
        distance = new long[N];
        Arrays.fill(distance, -1);
        checkCnt = new int[N];
        for (int i = 0; i < N; i++)
            verList[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken()) - 1;
            int V = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());
            verList[U].add(new Point(V, dist));
        }
        pq = new PriorityQueue<>();
        pq.add(new Point(0, 0));
        solve();
        for (int i = 0; i < N; i++) {
            if(checkCnt[i] != K)
                distance[i] = -1;
            sb.append(distance[i]).append("\n");
        }System.out.println(sb);
    }

    private static void solve() {
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (checkCnt[p.to] >= K)
                continue;
            checkCnt[p.to]++;
            distance[p.to] = p.dist;
            for (Point item : verList[p.to]) {
                if (checkCnt[item.to] >= K)
                    continue;
                pq.add(new Point(item.to, item.dist + p.dist));
            }
        }
    }
}
