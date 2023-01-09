package com.mrcAlgo.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baaaaaaaduk2_16988 {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static Queue<Point> q;
    static int maxDieCount;

    static class Point {
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
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        maxDieCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, 0);

        System.out.println(maxDieCount);
    }


    private static void solve(int cnt, int sy, int sx) {
        if (cnt == 2) {
            maxDieCount = Math.max(maxDieCount, getDieCount());
            return;
        }

        for (int i = sy; i < N; i++) {
            int j = 0;
            if (sy == i) { // 같은열인 경우엔 처음 선택한 위치 이후부터 선택하도록
                j = sx;
            }
            for (; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    solve(cnt + 1, i, j);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int getDieCount() {
        int dieCount = 0;
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2 && !visit[i][j]) {
                    q = new LinkedList<>();
                    visit[i][j] = true;
                    q.add(new Point(i, j));
                    dieCount += checkDie();
                }
            }
        }
        return dieCount;
    }

    private static int checkDie() {
        Boolean [][] vi = new Boolean[N][M];
        Boolean [][] vi2 = new Boolean[N/4][M/2];
        int cnt = 1; //시작지점
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        boolean isSafe = false;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int ty = p.y + dy[d];
                int tx = p.x + dx[d];
                if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[ty][tx] || map[ty][tx] == 1)
                    continue;
                if (map[ty][tx] == 0) { // 0이 인접해 있는 경우가 있다면 생존
                    isSafe = true;
                }
                if (map[ty][tx] == 2) {
                    visit[ty][tx] = true;
                    cnt++;
                    q.add(new Point(ty, tx));
                }
            }
        }
        if (!isSafe) {
            return cnt;
        }
        return 0;
    }
}
