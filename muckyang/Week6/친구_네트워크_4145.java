package com.mrcAlgo.Week6_유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 친구_네트워크_4145 {

    static int T, F;
    static Map<String, Integer> hm;
    static int[] group;
    static int[] number;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        hm = new HashMap<>();
        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());
            F = Integer.parseInt(st.nextToken());
            group = new int[F * 2];
            number = new int[F * 2];

            for (int i = 0; i < F * 2; i++) {
                group[i] = i;
                number[i] = 1;
            }

            int groupIdx = 0;
            for (int f = 0; f < F; f++) {
                st = new StringTokenizer(br.readLine());
                String nameA = st.nextToken();
                String nameB = st.nextToken();

                if (!hm.containsKey(nameA)) {
                    hm.put(nameA, groupIdx++);
                }

                if (!hm.containsKey(nameB)) {
                    hm.put(nameB, groupIdx++);
                }

                int aGroupIdx = hm.get(nameA);
                int bGroupIdx = hm.get(nameB);

                sb.append(union(aGroupIdx,bGroupIdx)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            group[b] = a;
            number[a] += number[b];
        }

        return number[a];
    }

    private static int find(int idx) {
        if (idx == group[idx])
            return idx;
        return group[idx] = find(group[idx]);
    }
}
