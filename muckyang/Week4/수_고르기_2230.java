package com.mrcAlgo.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수_고르기_2230 {
    static int N, M;
    static int[] arr;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        res = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int headIdx = 0;
        int tailIdx = 1;
        while (tailIdx < N && headIdx < N) {
            int diff = Math.abs(arr[headIdx] - arr[tailIdx]);
            if (M <= diff) {
                headIdx++;
                if (diff < res)
                    res = diff;
                continue;
            }
            tailIdx++;
        }
        System.out.println(res);
    }
}
