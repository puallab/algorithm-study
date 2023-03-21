package com.mrcAlgo.Week10_재도전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문명_14868 {
    static int N, K;
    static int[][] map;
    static int[] grp;
    static Queue<Point> q;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static int mergeCnt, time;

    public static class Point {
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
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        grp = new int[K + 1];
        q = new LinkedList<>();
        for (int i = 1; i <= K; i++) {
            grp[i] = i;
            st = new StringTokenizer(br.readLine());
            int civilX = Integer.parseInt(st.nextToken()) - 1;
            int civilY = Integer.parseInt(st.nextToken()) - 1;
            map[civilY][civilX] = i;
            q.add(new Point(civilY, civilX));
        }
        mergeCnt = 1;
        time = 0;
        solve();
        System.out.println(time);
    }

    private static void solve() {
        while (checkMerge() < K) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Point p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int ny = dy[d] + p.y;
                    int nx = dx[d] + p.x;
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N)
                        continue;

                    if (map[ny][nx] != 0) {
                        if (find(map[ny][nx]) != find(map[p.y][p.x])) {
                            mergeCnt++;
                            union(map[ny][nx], map[p.y][p.x]);
                        }
                    } else {
                        map[ny][nx] = map[p.y][p.x];
                        q.add(new Point(ny, nx));
                    }
                }
            }
            time++;
        }

    }

    private static int checkMerge() {
        for (int k = 0; k < q.size(); k++) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + p.y;
                int nx = dx[d] + p.x;
                if (ny < 0 || nx < 0 || ny >= N || nx >= N)
                    continue;
                if (map[ny][nx] != 0) {
                    if (find(map[ny][nx]) != find(map[p.y][p.x])) {
                        mergeCnt++;
                        union(map[ny][nx], map[p.y][p.x]);
                    }
                }
            }
            q.add(p);
        }
        return mergeCnt;
    }

    private static void union(int a, int b) {
        grp[find(a)] = find(b);
    }

    private static int find(int idx) {
        if (idx == grp[idx])
            return idx;

        return grp[idx] = find(grp[idx]);
    }

}

