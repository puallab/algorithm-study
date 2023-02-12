package com.mrcAlgo.Week6_유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합의_표현_1717 {

    static int N, M;
    static int[] group;


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        group = new int[N + 1];
        for (int i = 0; i <= N; i++)
            group[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int rootA = find(a);
            int rootB = find(b);
            if (type == 0) { // Union
                if (rootA != rootB)
                    group[rootB] = rootA;
            } else { // Find
                if (rootA == rootB) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int find(int idx) {
        if (group[idx] == idx)
            return idx;
        return find(group[idx]);
    }

}
