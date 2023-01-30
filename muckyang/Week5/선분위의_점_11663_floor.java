package com.mrcAlgo.Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 선분위의_점_11663_floor {
    ///Arrays.sort 개열받네

    static int N, M;
    static int[] num;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        num[0] = -1;
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        num[N + 1] = Integer.MAX_VALUE;
        Arrays.sort(num);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int frontCnt = (int) Math.floor(search(A));
            int endCnt = (int) Math.round(search(B));
            int result = endCnt - frontCnt;
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static double search(int target) {
        int head = 0;
        int tail = N + 1;
        while (head + 1 < tail) {
            int mid = (head + tail) / 2;
            if (num[mid] <= target)
                head = mid;
            else
                tail = mid;
        }
        if (num[head] == target) {// 선분의 양끝과 일치하면 -0.5
            return head - 0.5;
        }
        return head;
    }

}