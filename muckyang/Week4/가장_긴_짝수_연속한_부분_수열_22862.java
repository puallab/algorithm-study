package com.mrcAlgo.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장_긴_짝수_연속한_부분_수열_22862 {

    static int N;
    static int mergeCnt;
    static int[] linkCnt;
    static int maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        mergeCnt = Integer.parseInt(st.nextToken());
        linkCnt = new int[N+1];
        maxCount = 0;
        st = new StringTokenizer(br.readLine());
        int evenCnt = 0;
        int idx = 0;
        for (int i = 0; i <= N; i++) {
            if (i == N) { // 마지막 체크
                linkCnt[idx] = evenCnt;
                idx++;
            } else if (Integer.parseInt(st.nextToken()) % 2 == 0) {
                evenCnt++;
            } else {
                linkCnt[idx] = evenCnt;
                evenCnt = 0;
                idx++;
            }
        }

        int sum = 0;
        for (int i = 0; i <= mergeCnt; i++) {
            if (i == idx) { // mergeCnt > N
                break;
            }
            sum += linkCnt[i];
        }
        maxCount = sum;
        for (int i = mergeCnt + 1; i < idx; i++) {
            sum -= linkCnt[i - (mergeCnt + 1)];
            sum += linkCnt[i];
            if (maxCount < sum)
                maxCount = sum;
        }
        System.out.println(maxCount);
    }
}
