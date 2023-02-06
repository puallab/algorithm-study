package com.mrcAlgo.Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 입국심사_3079 {
    static int N;
    static long M;
    static long[] testTime;
    static long maxCnt;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        testTime = new long[N];
        answer = 0;
        for (int i = 0; i < N; i++) {
            testTime[i] = Long.parseLong(br.readLine());
        }
        long start = 0L;
        long end = 1000000000L * 1000000000;
        maxCnt =  end;
        while (start <= end) {
            long mid = (start + end) / 2;
            if (canPasscheck(mid)) {
                end = mid - 1;
                answer = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(answer);

    }

    private static boolean canPasscheck(long time) {
        long passCnt = 0;
        for (int i = 0; i < N; i++) {
            passCnt += (time / testTime[i]);
            if(maxCnt < passCnt)
                return true;
        }
            if (passCnt < M)
                return false;
        return true;
    }
}
