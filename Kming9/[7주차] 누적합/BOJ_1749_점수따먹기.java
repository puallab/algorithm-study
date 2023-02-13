package Week7_누적합;

import java.io.*;
import java.util.*;

public class BOJ_1749_점수따먹기 {
    static int N, M, arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];

        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= M; x++) {
                arr[y][x] = Integer.parseInt(st.nextToken()) + arr[y - 1][x] + arr[y][x - 1] - arr[y - 1][x - 1];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int y1 = 0; y1 < N; y1++) {
            for (int x1 = 0; x1 < M; x1++) {
                for (int y2 = y1 + 1; y2 <= N; y2++) {
                    for (int x2 = x1 + 1; x2 <= M; x2++) {
                        max = Math.max(max, arr[y2][x2] - arr[y1][x2] - arr[y2][x1] + arr[y1][x1]);
                    }
                }
            }
        } //812ms

//        for (int y2 = 1; y2 <= N; y2++) {
//            for (int x2 = 1; x2 <= M; x2++) {
//                for (int y1 = 0; y1 < y2; y1++) {
//                    for (int x1 = 0; x1 < x2; x1++) {
//                        max = Math.max(max, arr[y2][x2] - arr[y1][x2] - arr[y2][x1] + arr[y1][x1]);
//                    }
//                }
//            }
//        } // 680ms
        System.out.println(max);
    }
}
