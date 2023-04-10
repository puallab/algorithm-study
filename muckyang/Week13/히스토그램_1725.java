package com.mrcAlgo.Week13_오마카세;

import java.util.*;
import java.io.*;

public class 히스토그램_1725 {
    static int N;
    static int[] hArr;
    static Stack<Integer> idxStack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hArr = new int[N + 2];
        idxStack = new Stack<>();

        hArr[0] = 0;
        hArr[N + 1] = 0;
        for (int i = 1; i <= N; i++)
            hArr[i] = Integer.parseInt(br.readLine());
        int max = 0;
        for (int idx = 0; idx <= N + 1; idx++) {
            int nextHeight = hArr[idx];
            // 이전의 높이보다 작으면 반복하면서 제거
            while (!idxStack.isEmpty() && nextHeight < hArr[idxStack.peek()]) {
                int peekIdx = idxStack.pop();
                int topHeight = hArr[peekIdx];
                max = Math.max(max, (idx -1 - idxStack.peek()) * topHeight);
            }
            idxStack.push(idx);
        }
        System.out.println(max);

    }
}
