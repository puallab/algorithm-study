package com.mrcAlgo.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알파_틱택토_16571 {

    static int[][] map;
    static int winColor;
    static int wantWin;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cntX = 0;
        int cntO = 0;

        result = -1;//패배
        map = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int color = Integer.parseInt(st.nextToken());
                map[i][j] = color;
                if (color == 1) {
                    cntX++;
                } else if (color == 2) {
                    cntO++;
                }
            }

        }
        int startColor = 1;
        if (cntX != cntO) {
            startColor = 2;
        }
        wantWin = startColor;
        System.out.println(cntX + ", " + cntO + " : " + wantWin);

        solve(cntO + cntX, startColor);

        String answer = "";
        if (result == -1) {
            answer = "L";
        } else if (result == 0) {
            answer = "D";
        } else {
            answer = "W";
        }
        System.out.println(answer);
    }

    static void solve(int cnt, int nextColor) {
        if (result == 1) {
            return;
        }

        isGameEnd();
        System.out.println(cnt +": " + nextColor + " / " + result);
        if (cnt == 9) {
            result = Math.max(result, 0);
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (map[i / 3][i % 3] == 0) {
                map[i / 3][i % 3] = nextColor;
//                solve(cnt + 1, (nextColor) % 2 +1);
                solve(cnt + 1, nextColor == 2 ? 1 : 2);
                map[i / 3][i % 3] = 0;
            }
        }
    }

    static void isGameEnd() {
        //가로체크
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == map[i][1] && map[i][0] == map[i][2]) {
                if (map[i][0] == wantWin) {
                    result = 1;
                }
                return;
            }
        }
        //세로체크
        for (int j = 0; j < 3; j++) {
            if (map[0][j] == map[1][j] && map[0][j] == map[2][j]) {
                if (map[0][j] == wantWin) {
                    result = 1;
                }
                return;
            }
        }
        //대각선
        if (map[0][0] == map[1][1] && map[0][0] == map[2][2]) {
            if (map[0][0] == wantWin) {
                result = 1;
                return;
            }
        }

        if (map[0][2] == map[1][1] && map[0][2] == map[2][0]) {
            if (map[0][2] == wantWin) {
                result = 1;
            }

        }
    }
}
