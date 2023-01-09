package com.mrcAlgo.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 늑대_사냥꾼_2917 {

    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static Queue<Point> q;
    static PriorityQueue<Point> pq;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int sx, sy, ex, ey;

    private static class Point implements Comparable<Point> {
        int y, x, dist;

        public Point(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point p) {
            return p.dist - this.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        q = new LinkedList<>();
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                map[i][j] = -1;
                if (c == 'V') {
                    sy = i;
                    sx = j;
                } else if (c == 'J') {
                    ey = i;
                    ex = j;
                } else if (c == '+') {
                    map[i][j] = 0;
                    q.add(new Point(i, j, 0));
                }
            }
        }

        bfs(); // 나무로부터의 거리 지정

        pq = new PriorityQueue<>();
        pq.add(new Point(sy, sx, map[sy][sx]));

        solve();
    }

    private static void solve() {
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (ey == p.y && ex == p.x) {
                System.out.println(p.dist);
                return;
            }
            if (visit[p.y][p.x])
                continue;
            visit[p.y][p.x] = true;

            for (int d = 0; d < 4; d++) {
                int ty = dy[d] + p.y;
                int tx = dx[d] + p.x;
                if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[ty][tx]) continue;
                pq.add(new Point(ty, tx, Math.min(p.dist, map[ty][tx])));
            }

        }
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int ty = dy[d] + p.y;
                int tx = dx[d] + p.x;
                if (ty < 0 || tx < 0 || ty >= N || tx >= M || map[ty][tx] != -1) continue;
                map[ty][tx] = p.dist + 1;
                q.add(new Point(ty, tx, map[ty][tx]));
            }
        }
    }
}
