package Week10_재도전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2571_색종이_3 {
    static int N = 100, K = 10, M, arr[][], ans;
    static boolean papers[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][N + 1];
        papers = new boolean[N + 1][N + 1];
        ans = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int fx = x + K;
            int fy = y + K;
            for (; y < fy; y++) {
                for (int tx = x; tx < fx; tx++) {
                    arr[y][tx] = 1;
                    papers[y][tx] = true;
                }
            }
        }

        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                arr[y][x] += arr[y - 1][x] + arr[y][x - 1] - arr[y - 1][x - 1];
            }
        }

        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (!papers[y][x]) continue;

                int fx = N + 1;
                for (int ny = y; ny <= N; ny++) {
                    for (int nx = x; nx < fx; nx++) {
                        if (!papers[ny][nx]) {
                            fx = nx;
                            break;
                        }
                        ans = Math.max(ans, arr[ny][nx] - arr[ny][x - 1] - arr[y - 1][nx] + arr[y - 1][x - 1]);
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
