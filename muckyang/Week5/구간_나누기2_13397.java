package com.mrcAlgo.Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간_나누기2_13397 {
    static int N, M;
    static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        number = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            number[i] = Integer.parseInt(st.nextToken());

    }
}
