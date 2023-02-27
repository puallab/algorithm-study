package com.mrcAlgo.Week8_세그먼트_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간_합_구하기_2042 {
    private static class SegmentTree {
        static int n;
        static long[] range;

        public SegmentTree(long[] array) {
            n = array.length;
            range = new long[n * 4];
            init(array, 0, n - 1, 1);
        }

        //세그먼트 트리 초기화
        private long init(long[] array, int left, int right, int node) {
            if (left == right)
                return range[node] = array[left];
            int mid = (int) ((long) left + right) / 2;
            long leftSum = init(array, left, mid, node * 2);
            long rightSum = init(array, mid + 1, right, node * 2 + 1);
            return range[node] = leftSum + rightSum;
        }

        // 구간의 합 질의 메소드
        private long query(long l, long r, long node, int nodeL, int nodeR) {
            // 구간에 속하지 않을때
            if (r < nodeL || nodeR < l)
                return 0;

            // 구간에 속하는 경우
            if (l <= nodeL && nodeR <= r)
                return range[(int) node];
            // 일부분만 구간에 속하는 경우
            int mid = (int) ((long) nodeL + nodeR) / 2;
            return query(l, r, node * 2, nodeL, mid)
                    + query(l, r, node * 2 + 1, mid + 1, nodeR);
        }

        long query(long l, long r) {
            return query(l, r, 1, 0, n - 1);
        }

        // 값 갱신시 호출 메서드
        private long update(long idx, long newValue, int node, int nodeL, int nodeR) {
            //idx가 구간에 포함되지 않는경우 패스
            if (idx < nodeL || nodeR < idx)
                return range[node];
            //리프 노드까지 도착한 경우
            if (nodeL == nodeR)
                return range[node] = newValue;

            int mid = (int) ((long) nodeL + nodeR) / 2;
            return range[node] = update(idx, newValue, node * 2, nodeL, mid)
                    + update(idx, newValue, node * 2 + 1, mid + 1, nodeR);
        }

        void update(long idx, long newValue) {
            update(idx, newValue, 1, 0, n - 1);
        }
    }

    static int N, M, K;
    static long[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new long[N];

        for (int i = 0; i < N; i++)
            list[i] = Long.parseLong(br.readLine());

        StringBuilder sb = new StringBuilder();
        SegmentTree sTree = new SegmentTree(list);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());

            if (type == 1) {
                sTree.update(n - 1, m);
            } else if (type == 2) {
                sb.append(sTree.query(n - 1, m - 1)).append("\n");
            }
        }
        System.out.println(sb);
    }
}
