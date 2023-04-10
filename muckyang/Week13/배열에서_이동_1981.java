package com.mrcAlgo.Week13_오마카세;

import java.io.*;
import java.util.*;

public class 배열에서_이동_1981 {
    static int N;
    static Queue<Point> q;
    static int[][] map;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        int min, max;
        min = 10000;
        max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        q = new LinkedList<>();
        int res = 0;
        int l = 0;
        int r = max - min;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            boolean flag = false;
            for (int i = min; i < max; i++) {
                if (flag = bfs(i, i + mid))
                    break;
            }
            if (!flag) {
                l = mid + 1;
            } else {
                res = mid;
                r = mid - 1;
            }
        }
        System.out.println(res);
    }

    private static boolean bfs(int min, int max) {
        q = new LinkedList<Point>();
        boolean[][] visit = new boolean[N][N];
        if (!safe(map[0][0], min, max))
            return false;
        visit[0][0] = true;
        q.add(new Point(0, 0));
        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.y == N - 1 && p.x == N - 1)
                return true;
            for (int d = 0; d < 4; d++) {
                int ty = dy[d] + p.y;
                int tx = dx[d] + p.x;
                if (ty < 0 || tx < 0 || ty >= N || tx >= N || visit[ty][tx] || !safe(map[ty][tx], min, max))
                    continue;
                visit[ty][tx] = true;
                q.add(new Point(ty, tx));
            }
        }
        return false;
    }

    private static boolean safe(int num, int min, int max) {
        if (num < min || num > max)
            return false;
        return true;
    }
}

