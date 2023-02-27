package com.mrcAlgo.Week7_구간합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 점수_따먹기_1749 {
    static int N, M;
    static int[][] sum;
    static int maxSum;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                sum[i][j] = Integer.parseInt(st.nextToken()) + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
        maxSum = Integer.MIN_VALUE;
        for (int i1 = 1; i1 <= N; i1++) {
            for (int j1 = 1; j1 <= M; j1++) {
                for (int i2 = 0; i2 < i1; i2++) {
                    for (int j2 = 0; j2 < j1; j2++) {
                        int res = sum[i1][j1] - sum[i1][j2] - sum[i2][j1] + sum[i2][j2];
                        maxSum = Math.max(res, maxSum);
                    }
                }
            }
        }
        System.out.println(maxSum);
    }
}


