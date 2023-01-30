package com.mrcAlgo.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 회전초밥_15961_eatCountMap {
    static int N;
    static int d, k, c;
    static int[] sushi;
    static int[] eatCount;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        res = 0;
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[N];
        eatCount = new int[d + 1];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            if (eatCount[sushi[i]] == 0)
                cnt++;
            eatCount[sushi[i]]++;
        }
        if (eatCount[c] == 0)
            cnt++;
        eatCount[c]++;

        int firstIdx = 0;
        int lastIdx = k;
        for (int i = 0; i < N; i++, lastIdx++, firstIdx++) {

            if (res < cnt) {
                res = cnt;
            }
            firstIdx %= N;
            lastIdx %= N;
            // 앞접시 제거
            if (eatCount[sushi[firstIdx]] == 1)
                cnt--;
            eatCount[sushi[firstIdx]]--;
            // 뒷접시 추가
            if (eatCount[sushi[lastIdx]] == 0)
                cnt++;
            eatCount[sushi[lastIdx]]++;
        }
        System.out.println(res);
    }
}
