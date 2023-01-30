package com.mrcAlgo.Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기_설치_2110 {
    static int N, C;
    static long[] house;

    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        res = 0;
        house = new long[N];
        for (int i = 0; i < N; i++)
            house[i] = Long.parseLong(br.readLine());
        Arrays.sort(house);

        long start = 1L;
        long end = 1000000001L;
        while (start <= end) {
            long mid = (start + end) / 2;
            long next = house[0] + mid; // 첫번째 집은 무조건 선택
            int count = 1;
            for (int i = 1; i < N; i++) {
                if (next <= house[i]) {
                    count++;
                    next = house[i] + mid;
                }
            }
            if (C <= count) {
                res = Math.max(res, mid);
                start = mid + 1;
            } else
                end = mid - 1;
        }
        System.out.println(res);
    }
}
