package com.mrcAlgo.Week7_구간합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 두_배열의_합_2143 {
    static int T, N, M;
    static int[] nList;
    static int[] mList;
    static Map<Integer, Long> nHm;
    static Map<Integer, Long> mHm;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        nList = new int[N + 1];
        nHm = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nList[i] = nList[i - 1] + Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                int num = nList[i] - nList[j];
                if (!nHm.containsKey(num))
                    nHm.put(num, 1L);
                else
                    nHm.put(num, nHm.get(num) + 1);
            }
        }

        mHm = new HashMap<>();
        M = Integer.parseInt(br.readLine());
        mList = new int[M + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= M; i++) {
            mList[i] = mList[i - 1] + Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < i; j++) {
                int num = mList[i] - mList[j];
                if (!mHm.containsKey(num))
                    mHm.put(num, 1L);
                else
                    mHm.put(num, mHm.get(num) + 1);
            }
        }
        long cnt = 0;
        for (Integer key : nHm.keySet()) {
            long nCnt = nHm.get(key);
            int target = T - key;
            if (mHm.containsKey(target)) {
                long mCnt = mHm.get(target);
                cnt += nCnt * mCnt;
            }
        }
        System.out.println(cnt);
    }
}
