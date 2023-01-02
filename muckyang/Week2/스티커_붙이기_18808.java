package com.mrcAlgo.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커_붙이기_18808 {
    static int N, M, K;
    static int R, C;
    static boolean[][] fullMap;
    static boolean[][] map;
    static int response;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fullMap = new boolean[N][M];
        response = 0;
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new boolean[R][C];
            int stickerCnt = 0;
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    if (st.nextToken().equals("1")) {
                        map[i][j] = true;
                        stickerCnt++;
                    }
                }
            }
            if (solve(k)) {
                response += stickerCnt;
            }
        }
        System.out.println(response);
    }

    private static boolean solve(int idx) {
        for (int d = 0; d < 4; d++) {
            R = map.length - 1;
            C = map[0].length - 1;
            for (int i = 0; i < N - R; i++) {
                for (int j = 0; j < M - C; j++) {
                    if (canPut(i, j)) {
                        put(i, j);
                        return true;
                    }
                }
            }
            rotate(idx);
        }
        return false;
    }

    private static boolean canPut(int sy, int sx) {
        for (int i = 0; i <= R; i++) {
            for (int j = 0; j <= C; j++) {
                if (fullMap[i + sy][j + sx] && map[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void put(int sy, int sx) {
        for (int i = 0; i <= R; i++) {
            for (int j = 0; j <= C; j++) {
                if (map[i][j])
                    fullMap[i + sy][j + sx] = map[i][j];
            }
        }
    }

    private static void rotate(int idx) {
        int N = map.length;
        int M = map[0].length;
        boolean[][] temp = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = map[N - j - 1][i];
            }
        }

        map = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            System.arraycopy(temp[i], 0, map[i], 0, N);
        }
    }
}
