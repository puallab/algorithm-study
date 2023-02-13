package com.mrcAlgo.Week7_구간합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이차원_배열의_합_2167 {
    static int N, M, K;
    static int[][] sum;

    public static void main(String[] args) throws Exception {
        StringBuilder sb= new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int num = Integer.parseInt(st.nextToken());
                sum[i][j] = num + sum[i - 1][j] + sum[i][j - 1] - sum[i-1][j-1];
            }
        }
        K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int calcNum = sum[x][y] - sum[i][y]- sum[x][j] + sum[i][j];
            sb.append(calcNum).append("\n");
        }

        System.out.println(sb);
    }
}
