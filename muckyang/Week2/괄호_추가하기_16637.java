package com.mrcAlgo.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 괄호_추가하기_16637 {

    static int N, calcCnt;
    static int[] number;
    static int[] calcNumber;
    static char[] calc;

    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        res = Integer.MIN_VALUE;
        N = Integer.parseInt(str);
        calcCnt = N / 2;
        number = new int[calcCnt + 1];
        calc = new char[calcCnt];
        calcNumber = new int[100];

        str = br.readLine();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) { //number
                number[i / 2] = Integer.parseInt(str.charAt(i) + "");
            } else { // -, + ,*
                calc[i / 2] = str.charAt(i);
            }
        }

        for (int i = 0; i < calcCnt; i++) { // 0 ,1 -> calc[1] , 2,3 -> calc[23]
            int j = (i + 1);
            calcNumber[i * 10 + j] = calc(j, number[i], number[j]);
        }

        combination(0, 0);

        System.out.println(res);
    }

    private static void combination(int idx, int flag) {
        solve(flag);
        if (idx >= calcCnt) {
            return;
        }

        for (int i = idx; i < calcCnt; i++) {
            if (i == 0 || (flag & 1 << (i - 1)) == 0) {
                combination(i + 2, flag | (1 << i));
            }
        }
    }

    private static void solve(int flag) {
        int calcResult = 0;
        for (int i = 0; i <= calcCnt; i++) {
            if ((flag & (1 << i)) != 0) {
                if (i == 0) {
                    calcResult += calcNumber[i + 1];
                } else {
                    calcResult = calc(i, calcResult, calcNumber[i * 10 + i + 1]);
                }
                i++;
            } else {
                if (i == 0) {
                    calcResult = number[0];
                } else {
                    calcResult = calc(i, calcResult, number[i]);
                }
            }
        }

        res = Math.max(calcResult, res);
    }

    private static int calc(int idx, int i, int j) {
        int num = 0;
        if (calc[idx - 1] == '-')
            num = i - j;
        if (calc[idx - 1] == '+')
            num = i + j;
        if (calc[idx - 1] == '*')
            num = i * j;
        return num;
    }
}
