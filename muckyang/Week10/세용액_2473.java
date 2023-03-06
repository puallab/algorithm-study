package com.mrcAlgo.Week10_재도전;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 세용액_2473 {

    static int N;
    static long[] list;
    static long res;
    static long[] resList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new long[N];
        resList = new long[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());

        }
        res = Long.MAX_VALUE;
        Arrays.sort(list);

        for (int i = 0; i < N; i++) {
            solve(i);
        }

        Arrays.sort(resList);

        for (int i = 0; i < resList.length; i++)
            System.out.print(resList[i] + " ");

    }

    private static void solve(int i) {
        int l = i == 0 ? 1 : 0;
        int r = i == N - 1 ? N - 2 : N - 1;
        long lrVal = Math.abs(list[i] + list[l] + list[r]);
        if (lrVal < res) {
            resList[0] = list[i];
            resList[1] = list[l];
            resList[2] = list[r];
            res = lrVal;
        }
        while (l < r) {
            int nextL = l + 1;
            int nextR = r - 1;

            if (nextL == i)
                nextL++;

            if (nextR == i)
                nextR--;

            if (nextR == l || r == nextL)
                break;
            long lp = Math.abs(list[i] + list[nextL] + list[r]);
            long rp = Math.abs(list[i] + list[l] + list[nextR]);

            if (lp < rp) {
                if (lp < res) {
                    resList[0] = list[i];
                    resList[1] = list[nextL];
                    resList[2] = list[r];
                    res = lp;
                }
                l = nextL;
            } else {
                if (rp < res) {
                    resList[0] = list[i];
                    resList[1] = list[l];
                    resList[2] = list[nextR];
                    res = rp;
                }
                r = nextR;
            }

        }
    }
}
