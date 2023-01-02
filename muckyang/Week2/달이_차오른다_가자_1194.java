package com.mrcAlgo.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이_차오른다_가자_1194 {

    static int N, M;
    /// . : 빈칸(0) , # : 벽(-1), abc : 열쇠, ABC : 문, 민식이 : 0, 출구 : 1
    static int[][] map;
    static boolean[][][] visit;
    static Queue<Point> q;
    static int sy, sx, ex, ey;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static int res;

    private static class Point {
        int y, x;
        int stepCnt;
        int hasKeys;

        public Point(int y, int x, int stepCnt, int hasKeys) {
            this.y = y;
            this.x = x;
            this.stepCnt = stepCnt;
            this.hasKeys = hasKeys;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[64][N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == '#') {
                    map[i][j] = -1;
                } else if (c == '.') {
                    map[i][j] = 0;
                } else if (c == '0') {
                    map[i][j] = 0;
                    sy = i;
                    sx = j;
                } else if (c == '1') {
                    map[i][j] = 1;
                    ey = i;
                    ex = j;
                } else if ('a' <= c && c <= 'z') { // 열쇠 100 이상
                    map[i][j] = 100 + c - 'a'; // 0~25
                } else if ('A' <= c && c <= 'Z') { // 문 200 이상
                    map[i][j] = 200 + c - 'A';
                }
            }
        }

        q = new LinkedList<>();
        q.add(new Point(sy, sx, 1, 0));

        visit[0][sy][sx] = true;
        res = -1;

        solve();

        System.out.println(res);
    }

    private static void solve() {
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Point p = q.poll();
                int py = p.y;
                int px = p.x;
                int pCnt = p.stepCnt;
                int pKey = p.hasKeys;
                for (int d = 0; d < 4; d++) {
                    int ty = py + dy[d];
                    int tx = px + dx[d];

                    if (isSafe(ty, tx) && !visit[pKey][ty][tx] && map[ty][tx] != -1) {
                        if (map[ty][tx] >= 200) { // 문
                            int door = map[ty][tx] - 200;
                            if ((pKey & 1 << door) != 0) { // 문에 대한 키가있는 경우
                                visit[pKey][ty][tx] = true;
                                q.add(new Point(ty, tx, pCnt + 1, pKey));
                            }
                        } else if (map[ty][tx] >= 100) { // 열쇠
                            visit[pKey][ty][tx] = true; // 키 습득 전
                            int nextKey = pKey | 1 << (map[ty][tx] - 100);
                            q.add(new Point(ty, tx, pCnt + 1, nextKey));
                        } else if (map[ty][tx] == 1) {
                            res = p.stepCnt;
                            return;
                        } else {
                            visit[pKey][ty][tx] = true;
                            q.add(new Point(ty, tx, pCnt + 1, pKey));
                        }
                    }
                }
            }
        }
    }

    private static boolean isSafe(int y, int x) {
        if (y >= 0 && x >= 0 && y < N && x < M)
            return true;
        return false;
    }
}
