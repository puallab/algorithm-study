package Week1_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14391_종이_조각 {
    static int N, M, num, map[][], ans;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = N * M;
        map = new int[N][M];
        visit = new boolean[N][M];
        ans = 0;

        char[] tmp;
        for (int y = 0; y < N; y++) {
            tmp = br.readLine().toCharArray();
            for (int x = 0; x < M; x++) {
                map[y][x] = tmp[x] - '0';
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                dfs(0, y, x, 0, 0, 0, 0);
            }
        }

        System.out.println(ans);
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void dfs(int dir, int y, int x, int cnt, int prev, int cur, int sum) {
        if (cnt == num) {
            sum += cur;
            if (sum > ans)
                ans = sum;

            return;
        }

        visit[y][x] = true;
        for (int i = 0; i < 4; i++) {
            if (cnt == 0) {
                dfs(i, y, x, cnt + 1, 0, map[y][x], sum);
            } else {
                int ty = y + dy[i];
                int tx = x + dx[i];

                if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[ty][tx]) continue;
                if (dir != i) {
                    dfs(i, ty, tx, cnt + 1, 0, map[ty][tx], sum + cur);
                    dfs(i, ty, tx, cnt + 1, map[y][x], calculate(i, map[y][x], map[ty][tx]), sum + prev);
                } else {
                    int num = calculate(i, cur, map[ty][tx]);
                    dfs(i, ty, tx, cnt + 1, cur, num, sum);
                }
            }
        }
        visit[y][x] = false;
    }

    public static int calculate(int dir, int a, int b) {
        return (dir == 1 || dir == 2) ? a * 10 + b : b * (int) Math.pow(10, (String.valueOf(a).length())) + a;
    }
}
