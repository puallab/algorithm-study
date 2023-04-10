package com.mrcAlgo.Week13_오마카세;

import java.io.*;
import java.util.*;

public class 공장_7578 {
    static int N;
    static int[] factory;
    static long res;

    private static class SegmentTree {
        int n;
        int[] arr;

        public SegmentTree(int size) {
            n = size * 4;
            arr = new int[n];
        }

        public int query(int idx) { // idx ~ N-1 까지의 합
            return query(idx, 1, 0, N - 1);
        }

        private int query(int idx, int node, int nodeL, int nodeR) {
            if (nodeR < idx)
                return 0;
            if (idx <= nodeL)
                return arr[node];

            int nodeM = nodeL + (nodeR - nodeL) / 2;
            return query(idx, node * 2, nodeL, nodeM) + query(idx, node * 2 + 1, nodeM + 1, nodeR);
        }

        public void update(int idx, int value) {
            update(idx, value, 1, 0, N - 1);
        }

        private void update(int idx, int value, int node, int nodeL, int nodeR) {
            if (idx < nodeL || nodeR < idx)
                return;
            arr[node]++;
            if (nodeL == nodeR) return;

            int nodeM = nodeL + (nodeR - nodeL) / 2;
            update(idx, value, node * 2, nodeL, nodeM);
            update(idx, value, node * 2 + 1, nodeM + 1, nodeR);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        factory = new int[N];
        HashMap<Integer, Integer> hm = new HashMap<>();
        SegmentTree segTree = new SegmentTree(N);
        res = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            factory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hm.put(Integer.parseInt(st.nextToken()), i);
        }

        for (int i = 0; i < N; i++) {
            int idx = hm.get(factory[i]);
            res += segTree.query(idx);
            segTree.update(idx, 1);
        }

        System.out.println(res);
    }
}
