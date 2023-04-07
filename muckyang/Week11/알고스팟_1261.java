package com.mrcAlgo.Week11_다익스트라;

import java.io.*;
import java.util.*;

public class 알고스팟_1261 {
    static int N, M;
    static boolean[][] map;
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { -1, 0, 1, 0 };

    public static class Point implements Comparable<Point> {
        int y, x;
        int count;

        public Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '0')
                    map[i][j] = true;
            }
        }
        System.out.println(solve());
    }

    private static int solve() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] v = new boolean[N][M];

        int minCnt = Integer.MAX_VALUE;
        pq.add(new Point(0, 0, 0));
        v[0][0] = true;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (p.y == N - 1 && p.x == M - 1) {
                minCnt = p.count;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int ty = dy[d] + p.y;
                int tx = dx[d] + p.x;
                if (ty < 0 || tx < 0 || ty >= N || tx >= M || v[ty][tx])
                    continue;
                v[ty][tx] = true;
                if (!map[ty][tx]) {
                    pq.add(new Point(ty, tx, p.count + 1));
                } else {
                    pq.add(new Point(ty, tx, p.count));
                }
            }
        }
        return minCnt;
    }
}

