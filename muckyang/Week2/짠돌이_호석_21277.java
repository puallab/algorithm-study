package com.mrcAlgo.Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 짠돌이_호석_21277 {

    static int N1, N2, M1, M2;
    static boolean[][] fullMap;
    static boolean[][] map1;
    static boolean[][] map2;
    static BufferedReader br;
    static StringTokenizer st;
    static int minAreaSize;
    static int fullY, fullX;

    static boolean visit[][];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        minAreaSize = Integer.MAX_VALUE;

        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());
        map1 = new boolean[N1][M1];

        settingMap(N1, M1, map1);

        st = new StringTokenizer(br.readLine());
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());

        map2 = new boolean[N2][M2];


        settingMap(N2, M2, map2);


        for (int d = 0; d < 4; d++) {

            fullY = N1 * 2 + N2;
            fullX = M1 * 2 + M2;

            fullMap = new boolean[fullY][fullX];
            for (int i = 0; i < N2; i++) {
                System.arraycopy(map2[i], 0, fullMap[i + N1], M1, M2);
            }
            for (int i = 0; i <= fullY - N1; i++) {
                for (int j = 0; j <= fullX - M1; j++) {
                    solve(i, j);
                }
            }
            rotate();
        }
        System.out.println(minAreaSize);
    }

    private static void solve(int y, int x) {
        int endY = 0;
        int endX = 0;

        int startY = Math.min(y, N1);
        int startX = Math.min(x, M1);

        for (int i = 0; i < N1; i++) {
            for (int j = 0; j < M1; j++) {
                if (map1[i][j] && fullMap[i + y][j + x]) {
                    return;
                }
                endX = j + x + 1;
            }

            endY = i + y + 1;
        }
        endX = Math.max(endX, M1 + M2);
        endY = Math.max(endY, N1 + N2);
        int areaSize = (endX - startX) * (endY - startY);

        if (minAreaSize <= areaSize)
            return;
        minAreaSize = areaSize;
    }

    private static void rotate() {

        int tempN = M1;
        int tempM = N1;
        boolean[][] temp = new boolean[tempN][tempM];

        for (int i = 0; i < tempN; i++) {
            for (int j = 0; j < tempM; j++) {
                temp[i][j] = map1[tempM - j - 1][i];
            }
        }

        map1 = new boolean[tempN][tempM];
        for (int i = 0; i < tempN; i++) {
            System.arraycopy(temp[i], 0, map1[i], 0, tempM);
        }
        N1 = tempN;
        M1 = tempM;
    }


    private static void settingMap(int n, int m, boolean[][] map) throws IOException {
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == '1') {
                    map[i][j] = true;
                }
            }
        }
    }
}
