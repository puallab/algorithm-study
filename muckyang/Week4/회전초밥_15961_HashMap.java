package com.mrcAlgo.Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 회전초밥_15961_HashMap {
    static int N;
    static int d, k, c;
    static int[] sushi;
    static Map<Integer, Integer> hm;
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
        hm = new HashMap<>();
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < k; i++) {
            if (hm.containsKey(sushi[i])) {
                hm.put(sushi[i], hm.get(sushi[i]) + 1);
            } else {
                hm.put(sushi[i], 1);
            }
        }
        if (hm.containsKey(c)) {
            hm.put(c, hm.get(c) + 1);
        } else {
            hm.put(c, 1);
        }
        int firstIdx = 0;
        int lastIdx = k;
        for (int i = 0; i < N; i++, lastIdx++, firstIdx++) {

            if (res < hm.size()) {
                res = hm.size();
            }
            firstIdx %= N;
            lastIdx %= N;
            // 앞접시 제거
            if (hm.get(sushi[firstIdx]) == 1) {
                hm.remove(sushi[firstIdx]);
            } else {
                hm.put(sushi[firstIdx] , hm.get(sushi[firstIdx]) - 1);
            }
            // 뒷접시 추가
            if (hm.containsKey(sushi[lastIdx])) {
                hm.put(sushi[lastIdx], hm.get(sushi[lastIdx]) + 1);
            } else {
                hm.put(sushi[lastIdx], 1);
            }
        }
        System.out.println(res);
    }
}
