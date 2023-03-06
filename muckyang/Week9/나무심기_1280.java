package com.mrcAlgo.Week8_세그먼트_트리;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무심기_1280 {
    static final int MOD_NUMBER = (int) (1e9 + 7);

    private static class SegTree {
        int n;
        long[] tree;

        public SegTree(int size, long[] arr) {
            n = size;
            tree = new long[n * 4];
        }

        private long query(int l, int r) {
            return queryRec(l, r, 1, 0, n - 1);
        }

        private long queryRec(int l, int r, int node, int nodeL, int nodeR) {
            if (nodeR < l || r < nodeL)
                return 0;

            if (nodeL <= l && r <= nodeR)
                return tree[node];

            int mid = (nodeL + nodeR) / 2;

            return queryRec(l, r, node * 2, nodeL, mid) % MOD_NUMBER
                    + queryRec(l, r, node * 2 + 1, mid + 1, nodeR) % MOD_NUMBER;
        }

        private void update(int idx, int value) {
            updateRec(idx, value, 1, 0, n - 1);
        }

        private long updateRec(int idx, int value, int node, int l, int r) {
            if (idx < l || r < idx)
                return 0;

            if (l == r)
                return tree[node] = value;

            int mid = (l + r) / 2;

            return updateRec(idx, value, node * 2, l, mid) % MOD_NUMBER
                    + updateRec(idx, value, node * 2 + 1, mid + 1, r) % MOD_NUMBER;
        }
    }

    static int N;
    static long[] list;
    static int maxVal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        maxVal = 0;
        list = new long[200000];
        SegTree distTree = new SegTree(200000, list);
        SegTree cntTree = new SegTree(200000, list);

        long res = 1;
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(br.readLine());
            maxVal = Math.max(maxVal, idx);
            long zToiDistSum = distTree.query(0, idx);
            long zToiDistCnt = cntTree.query(0, idx);
            long zeroToIdx = (idx * zToiDistCnt) - zToiDistSum;
            System.out.println(zeroToIdx);
            long iToMaxDistSum = distTree.query(idx, maxVal);
            long iToMaxDistCnt = cntTree.query(idx, maxVal);
            long iToMax = ((maxVal - idx) * iToMaxDistCnt) - iToMaxDistSum;

            System.out.println(iToMax);

            System.out.println(res);
            distTree.update(idx, idx);
            cntTree.update(idx, 1);
            if (i == 0) {
                continue;
            }
            res *= (zeroToIdx + iToMax) % MOD_NUMBER;

        }
        System.out.println(res);
    }
}
