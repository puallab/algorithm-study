package com.mrcAlgo.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N과 M (10)
public class N과M_10_15664 {
    static int N, M;
    static int[] num;
    static int[] numCnt;
    static int[] visit;
    static StringBuilder sb;
    static int typeCnt;

    public static void main(String[] args) throws IOException {

        sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N];
        numCnt = new int[10001];
        visit = new int[N];
        typeCnt = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (numCnt[number] == 0) {
                num[i] = number;
                typeCnt++;
            }
            numCnt[number]++;
        }
        Arrays.sort(num);

        combination(0, N - typeCnt);

        System.out.println(sb.toString());
    }

    static void combination(int cnt, int start) {
        if (cnt == M) {
            for (int i = 0; i < M; i++)
                sb.append(visit[i]).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            if (numCnt[num[i]] > 0) {
                visit[cnt] = num[i];
                numCnt[num[i]]--;
                combination(cnt + 1, i);
                numCnt[num[i]]++;
            }
        }
    }
}
