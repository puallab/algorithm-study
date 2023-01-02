package com.mrcAlgo.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종이_조각_14391 {
    static int N, M;
    static int[][] paper;
    static boolean[][] v;


    static int maxResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxResult = Integer.MIN_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                paper[i][j] = str.charAt(j) - '0';
            }
        }

        solve(0, 0, 0);
        System.out.println(maxResult);
    }

    private static void solve(int y, int x, int sum) {
        if (x == M) { // 행 변경
            y += 1;
            x = 0;
        }

        if (y == N) {
            maxResult = Math.max(sum, maxResult);
            return;
        }
        if (!v[y][x]) { // 아직 사용하지 않은 종이
            cutPaper(y, x, sum);
        } else { // 이미 사용한 종이인 경우
            solve(y, x + 1, sum);
        }
    }

    private static void cutPaper(int y, int x, int sum) {
        int colCnt = 0;
        int verCnt = 0;
        int addSum = 0;
        for (int i = y; i < N; i++, colCnt++) {
            if (v[i][x]) { // 중간에 사용한 종이 있으면 빠져나감
                break;
            }
            addSum *= 10;
            addSum += paper[i][x];
            for (int c = 0; c <= colCnt; c++) {
                v[y + c][x] = true;
            }
            solve(y, x + 1, sum + addSum);
            for (int c = 0; c <= colCnt; c++) {
                v[y + c][x] = false;
            }
        }

        addSum = 0;
        for (int j = x; j < M; j++, verCnt++) {
            if (v[y][j]) { // 중간에 사용한 종이 있으면 빠져나감
                break;
            }
            addSum *= 10;
            addSum += paper[y][j];
            for (int c = 0; c <= verCnt; c++) {
                v[y][x + c] = true;
            }
            solve(y, x + 1, addSum + sum);
            for (int c = 0; c <= verCnt; c++) {
                v[y][x + c] = false;
            }
        }
    }
}
