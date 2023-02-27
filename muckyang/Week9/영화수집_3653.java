package com.mrcAlgo.Week8_세그먼트_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 영화_수집_3653 {

    private static class SegmentTree {
        int n;
        int[] tree;

        public SegmentTree(int size, int[] arr) {
            n = size;
            tree = new int[size * 4];
            init();
        }

        private void init() {

            initRec(1, 0, n - 1);
        }

        private int initRec(int node, int l, int r) {
            if (l == r) {
                if (l < N) {
                    return tree[node] = 1;
                }
                return 0;
            }

            int mid = (l + r) / 2;

            return tree[node] = initRec(node * 2, l, mid) + initRec(node * 2 + 1, mid + 1, r);
        }

        private int query(int l, int r) {
            return queryRec(l, r, 1, 0, n - 1) - 1;
        }

        private int queryRec(int l, int r, int node, int nodeL, int nodeR) {
            if (nodeR < l || r < nodeL)
                return 0;
            if (l <= nodeL && nodeR <= r)
                return tree[node];
            int mid = (nodeL + nodeR) / 2;
            return queryRec(l, r, node * 2, nodeL, mid)
                    + queryRec(l, r, node * 2 + 1, mid + 1, nodeR);
        }

        private void update(int t, int number) {
            updateRec(t, 1, 0, n - 1, number);
        }

        private int updateRec(int t, int node, int nodeL, int nodeR, int number) {
            if (nodeR < t || t < nodeL)
                return tree[node];

            if (nodeL == nodeR)
                return tree[node] = number;

            int mid = (nodeL + nodeR) / 2;
            return tree[node] = updateRec(t, node * 2, nodeL, mid, number)
                    + updateRec(t, node * 2 + 1, mid + 1, nodeR, number);
        }
    }

    static int T, N, K;
    static int[] idxList;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            idxList = new int[N];

            for (int i = 0; i < N; i++)
                idxList[i] = N - i - 1;
            SegmentTree seg = new SegmentTree(N + K, new int[N + K]);

            for (int k = 0; k < K; k++) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int target = idxList[idx];
                int setIdx = N + k;
                sb.append(seg.query(target, setIdx)).append(" ");
                seg.update(target, 0);
                seg.update(setIdx, 1);
                idxList[idx] = setIdx;
            }
            sb.append("\n");

        }
        System.out.println(sb);

    }

}
