package com.mrcAlgo.Week6_유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 통신망_분할_17398_미완성 {

    static int N, M, Q;
    static int[] group;
    static long[] linkSize;
    static int[][] link;
    static int[] cutPoint;
    static long sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        group = new int[N];
        linkSize = new long[N];
        link = new int[2][M];
        cutPoint = new int[Q];
        boolean[] v = new boolean[M];// 삭제여부

        for (int i = 0; i < N; i++) {
            group[i] = i;
            linkSize[i] = 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            link[0][i] = from;
            link[1][i] = to;
        }

        for (int i = 0; i < Q; i++) {
            int cutIdx = Integer.parseInt(br.readLine()) - 1;
            cutPoint[i] = cutIdx;
            v[cutIdx] = true;
        }

        //연결
        for (int i = 0; i < M; i++) {
            if (!v[i]) {
                int fromGroup = find(link[0][i]);
                int toGroup = find(link[1][i]);
                union(fromGroup, toGroup);
            }
        }

        sum = 0;
        //끊는 연산 역순으로 진행하면서 Union(합치면서 값 누적)
        for (int i = Q - 1; i >= 0; i--) {

            int idx = cutPoint[i];
            int fromGroup = find(link[0][idx]);
            int toGroup = find(link[1][idx]);

            if (fromGroup != toGroup) { // 합쳐지는 경우
                sum += linkSize[fromGroup] * linkSize[toGroup];
                union(fromGroup, toGroup);
            }
        }
        System.out.println(sum);
    }

    private static void union(int u, int v) { //여기가 문제같은데....
        linkSize[v] += linkSize[u];
        group[u] = v;
    }

    private static int find(int idx) {
        if (group[idx] == idx)
            return idx;
        return group[idx] = find(group[idx]);
    }
}
