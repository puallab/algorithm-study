package com.mrcAlgo.Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 랜선_자르기_1654 {

    static int N, K;
    static int[] line;
    static int maxLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        line = new int[N];
        for (int i = 0; i < N; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }
        long start = 1;
        long end = Integer.MAX_VALUE;// 최대 길이

        while (start <= end) {
            long mid = (start + end) / 2;
            long count = 0;
            for (int i = 0; i < N; i++) {
                count += (long) line[i] / mid;
            }
            if (K <= count) {
                start = mid + 1;
                maxLength = Math.max(maxLength, (int)mid);
            } else {
                end = mid - 1;
            }
        }
        System.out.println(maxLength);
    }
}
