package com.mrcAlgo.Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 사냥꾼_8983 {
    static int M, N, L;

    static int[] firingPad;

    static int killCount;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        firingPad = new int[M + 1];
        killCount = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            firingPad[i] = Integer.parseInt(st.nextToken());
        }
        firingPad[M] = Integer.MAX_VALUE;
        Arrays.sort(firingPad);
        for (int j = 0; j < N; j++) {
            st = new StringTokenizer(br.readLine());
            int ax = Integer.parseInt(st.nextToken());
            int ay = Integer.parseInt(st.nextToken());
            hunting(ax, ay);
        }
        System.out.println(killCount);
    }

    private static void hunting(int x, int y) {
        int padX = selectPad(x);
        int dist = Math.abs(x - firingPad[padX]) + y;

        if (dist <= L) // 사정거리 안에 든다면
            killCount++;
    }

    private static int selectPad(int target) {
        int front = 0;
        int end = M;
        while (front + 1 < end) {
            int mid = (front + end) / 2;
            if (firingPad[mid] > target) {
                end = mid;
            } else {
                front = mid;
            }
        }
        // 양옆의 사로중 더 가까운 사로 선택
        if (Math.abs(firingPad[front] - target) < Math.abs(firingPad[end] - target))
            return front;
        else
            return end;
    }
}
