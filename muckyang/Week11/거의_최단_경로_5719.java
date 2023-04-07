package com.mrcAlgo.Week11_다익스트라;
import java.io.*;
import java.util.*;

public class 거의_최단_경로_5719 {

    private static class Point implements Comparable<Point> {
        int from, to, dist;

        public Point(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            if (this.dist > o.dist)
                return 1;
            return -1;
        }

        @Override
        public boolean equals(Object obj) {
            Point o = (Point) obj;
            if (this.from == o.from && this.to == o.to && this.dist == o.dist)
                return true;
            return false;
        }
    }

    static int N, M, S, D;
    static int[] distance;
    static ArrayList<Point>[] verList;
    static ArrayList<Point>[] backList;
    static HashSet<Point> allVerSet;
    static HashSet<Point> delSet;
    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0)
                break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            verList = new ArrayList[N];
            backList = new ArrayList[N];
            distance = new int[N];
            allVerSet = new HashSet<>();
            delSet = new HashSet<>();

            for (int i = 0; i < N; i++) {
                verList[i] = new ArrayList<>();
                backList[i] = new ArrayList<>();
                distance[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());
                Point vertex = new Point(U, V, dist);
                allVerSet.add(vertex);
                verList[U].add(vertex);
                backList[V].add(vertex);
            }

            solveMin();
            vertexDelete();
            reInitVerList();
            solveMin();

            if (distance[D] == Integer.MAX_VALUE)
                distance[D] = -1;
            sb.append(distance[D]).append("\n");
        }
        System.out.println(sb);
    }

    private static void reInitVerList() {
        distance = new int[N];

        for (int i = 0; i < N; i++) {
            verList[i] = new ArrayList<>();
            distance[i] = Integer.MAX_VALUE;
        }
        Iterator<Point> iter = allVerSet.iterator();
        while (iter.hasNext()) {
            Point p = iter.next();
            verList[p.from].add(p);
        }
    }

    private static void vertexDelete() {
        Point endPoint = new Point(-1, D, distance[D]);
        Queue<Point> q = new LinkedList<>();
        q.add(endPoint);
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (Point prev : backList[p.to]) {
                if (p.dist - prev.dist == distance[prev.from] && !delSet.contains(prev)) { // 최단 경로에 속하는 간선
                    q.add(new Point(-1, prev.from, distance[prev.from]));
                    delSet.add(prev);
                }
            }
        }
        allVerSet.removeAll(delSet);
    }

    private static void solveMin() {
        pq = new PriorityQueue<>();
        pq.add(new Point(0, S, 0));
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (distance[p.to] <= p.dist)
                continue;
            distance[p.to] = p.dist;
            for (Point next : verList[p.to]) {
                if (distance[next.to] <= next.dist + p.dist)
                    continue;
                pq.add(new Point(next.from, next.to, next.dist + p.dist));
            }
        }
    }
}