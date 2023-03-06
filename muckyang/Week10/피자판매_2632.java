package com.mrcAlgo.Week10_재도전;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 피자판매_2632 {

    static int K, A, B;
    static int n, m;

    static int[] pizzaSumA, pizzaSumB;
    static HashMap<Integer, Integer> hsa, hsb;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        pizzaSumA = new int[m + 1];
        pizzaSumB = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            pizzaSumA[i] = pizzaSumA[i - 1] + Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            pizzaSumB[i] = pizzaSumB[i - 1] + Integer.parseInt(br.readLine());
        }
        hsa = new HashMap<>();
        for (int i = 1; i <= 2 * m; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j)
                    continue;
                if (i > m) {
                    int end = i % m;
                    if (end >= j || end == 0)
                        continue;
                    int size = pizzaSumA[end] + (pizzaSumA[m] - pizzaSumA[j]);
                    hsa.put(size, hsa.getOrDefault(size, 0) + 1);
                } else {
                    if (i < j)
                        break;
                    int size = pizzaSumA[i] - pizzaSumA[j];
                    hsa.put(size, hsa.getOrDefault(size, 0) + 1);
                }
            }
        }

        hsb = new HashMap<>();
        for (int i = 1; i <= 2 * n; i++) {
            for (int j = 0; j < n; j++) {
                int end = i % n;
                if (i == j)
                    continue;
                if (i > n) {
                    if (end >= j || end == 0)
                        continue;
                    int size = pizzaSumB[end] + (pizzaSumB[n] - pizzaSumB[j]);
                    hsb.put(size, hsb.getOrDefault(size, 0) + 1);
                } else {
                    if (i < j)
                        break;
                    int size = pizzaSumB[i] - pizzaSumB[j];
                    hsb.put(size, hsb.getOrDefault(size, 0) + 1);
                }
            }
        }

        for (Integer Asize : hsa.keySet()) {
            int Bsize = K - Asize;
            int Acnt = hsa.get(Asize);
            if (hsb.containsKey(Bsize)) {
                cnt += Acnt * hsb.get(Bsize);
            }
        }
        if (hsa.containsKey(K)) {
            cnt += hsa.get(K);
        }
        if (hsb.containsKey(K)) {
            cnt += hsb.get(K);
        }

        System.out.println(cnt);
    }
}
