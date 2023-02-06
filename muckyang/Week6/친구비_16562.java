package com.mrcAlgo.Week6_유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 친구비_16562 {
    static int N, M, K;
    static int[] group;
    static int[] pay;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        pay = new int[N];
        group = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            group[i] = i;
            pay[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int rootA = find(a);
            int rootB = find(b);
            group[rootA] = rootB;
        }

        int[] minPay = new int[N];
        Arrays.fill(minPay, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            int root = find(i);
            minPay[root] = Math.min(pay[i], minPay[root]);
        }

        int sumPay = 0;
        for (int i = 0; i < N; i++) {
            if (minPay[i] <= 10000){
                sumPay += minPay[i];
            }
        }

        if(sumPay > K)
            System.out.println("Oh no");
        else
            System.out.println(sumPay);
    }

    private static int find(int idx) {
        if (group[idx] == idx)
            return idx;
        return find(group[idx]);
    }
}
