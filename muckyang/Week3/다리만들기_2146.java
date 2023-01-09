package com.mrcAlgo.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기_2146 {
    static int N;
    static boolean[][] defaultMap;
    static int[][] map;
    static boolean[][] visit;

    static Queue<Point> q, leafQueue;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int areaNum;
    static int minResult;

    private static class Point {
        int y, x, areaCode;

        public Point(int y, int x, int areaCode) {
            this.y = y;
            this.x = x;
            this.areaCode = areaCode;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        minResult = Integer.MAX_VALUE;
        defaultMap = new boolean[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    defaultMap[i][j] = true;
                }
            }
        }
        q = new LinkedList<>();
        areaNum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (defaultMap[i][j] && map[i][j] == 0) {
                    areaNum++;
                    map[i][j] = areaNum;
                    q.add(new Point(i, j, areaNum));
                    bfs();
                }

            }
        }
        // 다리길이 측정
        for (int areaCode = 1; areaCode <= areaNum; areaCode++) {
            visit = new boolean[N][N];
            leafQueue = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == areaCode) {
                        for (int d = 0; d < 4; d++) {
                            int ty = i + dy[d];
                            int tx = j + dx[d];
                            if (ty < 0 || tx < 0 || ty >= N || tx >= N || visit[i][j])
                                continue;
                            if (map[ty][tx] == 0) {
                                visit[i][j] = true;
                                leafQueue.add(new Point(i, j, areaCode));
                            }
                        }
                    }
                }
            }
            leafBfs();
        }
        System.out.println(minResult);
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int ty = p.y + dy[d];
                int tx = p.x + dx[d];
                if (ty < 0 || tx < 0 || ty >= N || tx >= N || !defaultMap[ty][tx] || map[ty][tx] != 0) continue;
                map[ty][tx] = p.areaCode;
                q.add(new Point(ty, tx, p.areaCode));
            }
        }
    }

    private static void leafBfs() {
        int step = 0;
        while (!leafQueue.isEmpty()) {
            int size = leafQueue.size();
            for (int s = 0; s < size; s++) {
                Point p = leafQueue.poll();
                for (int d = 0; d < 4; d++) {
                    int ty = p.y + dy[d];
                    int tx = p.x + dx[d];
                    if (ty < 0 || tx < 0 || ty >= N || tx >= N || visit[ty][tx] || map[ty][tx] == p.areaCode) continue;

                    if (map[ty][tx] == 0) {
                        visit[ty][tx] = true;
                        leafQueue.add(new Point(ty, tx, p.areaCode));
                        continue;
                    }
                    minResult = Math.min(minResult, step);
                    return;
                }
            }
            step++;
            if(minResult <= step)
                return;
        }
    }
}
