package com.mrcAlgo.Week8_세그먼트_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 사탕 맛의 정도가 1 ~ 1000000 => 1000000개의 리프 노드를 갖는 트리를 생성
 * 각 리프 노드는 해당 맛의 사탕 갯수를 나타내고,
 * 상위 노드는 자녀 노드(사탕 수)의 합으로 구성한다.
 *
 * IF [A = 1 인 경우] B 순위의 사탕을 꺼냄
 *     최상단 노드부터 시작
 *     IF [왼쪽 노드의 값이 B보다 크다면]
 *          오른쪽 노드로 이동하면서 (왼쪽노드 갯수 만큼 B에서 빼줌)
 *          => 순위 개념이므로 왼쪽 노드의 값만큼 순위가 증가했다고 생각하는 것...
 *     ELSE [왼쪽 노드의 값이 B보다 작거나 같다면]
 *          왼쪽 노드 중에 꺼내야할 사탕이 존재
 *          => 왼쪽 노드를 기준으로 해당순위 사탕을 찾음
 *
 *     FIN => 루트노드 도착시 = 꺼낼 사탕! 값을 -1 해준다. (상위 노드도 모두 값을 빼줘야 함)
 * IF [A = 2 인 경우] B 맛의 사탕을 C개 만큼 변동시킴
 *      상단 노드부터 루트노드까지 C개 만큼 변동시키면 된다.
 */
public class 사탕상자_2243 {
    private static class SegmentTree {
        static int n;
        static int[] range;

        public SegmentTree(int[] array) {
            n = array.length;
            range = new int[n * 4];
        }

        // 사탕 1개 꺼낼때
        private int query(int rank, int node, int nodeL, int nodeR) {
            range[node]--;
            // 리프 노드 도착시
            if (nodeL == nodeR) {
                return nodeL;
            }
            // 구간에 속하지 않을때
            int lCnt = range[node * 2];
            int mid = (int) ((long) nodeL + nodeR) / 2;
            if (rank <= lCnt) { // 왼쪽 자식 노드에 속할 경우
                return query(rank, node * 2, nodeL, mid);
            } else {// 오른쪽 자식 노드로 이동시 rank에서 왼쪽 트리에 속하는 사탕 수 만큼 빼줌
                return query(rank - lCnt, node * 2 + 1, mid + 1, nodeR);
            }
        }

        int query(int rank) {
            return query(rank, 1, 0, n - 1);
        }


        private int update(int flavor, int candyCnt, int node, int nodeL, int nodeR) {
            //idx가 구간에 포함되지 않는경우 패스
            if (flavor < nodeL || nodeR < flavor)
                return range[node];

            range[node] += candyCnt;
            //리프 노드까지 도착한 경우
            if (nodeL == nodeR)
                return range[node];

            int mid = (int) ((long) nodeL + nodeR) / 2;
            return range[node] = update(flavor, candyCnt, node * 2, nodeL, mid)
                    + update(flavor, candyCnt, node * 2 + 1, mid + 1, nodeR);
        }

        void update(int flavor, int candyCnt) {
            update(flavor, candyCnt, 1, 0, n - 1);
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int[] list = new int[1000001];
        SegmentTree segTree = new SegmentTree(list);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (A == 1) { // B번째 순위 사탕 꺼내는 경우
                sb.append(segTree.query(B)).append("\n");
            } else if (A == 2) {
                int C = Integer.parseInt(st.nextToken());
                segTree.update(B, C);
            }
        }

        System.out.println(sb);
    }
}
