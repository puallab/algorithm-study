package Week2_백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18808_스티커_붙이기 {
    static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "y: " + this.y + ", x:" + this.x;
        }
    }

    static int N, M, ans = 0;
    static int R, C, sticker[][];
    static boolean map[][];
    static ArrayList<Pos> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];

        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            sticker = new int[R][C];

            for (int y = 0; y < R; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < C; x++) {
                    sticker[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            solve();
        }

        for (int a = 0; a < N; a++)
            System.out.println(Arrays.toString(map[a]));
        System.out.println();
        System.out.println(ans);
    }

    public static void solve() {
        savePoint();

        int rotation = 0;
        while (!isPossible()) {
            if (++rotation == 4)
                break;

            rotate();
            print();
            savePoint();
        }
    }

    public static boolean isPossible() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (dfs(y, x, 0, list.size())) {
                    ans += list.size();
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean dfs(int y, int x, int cnt, int size) {
        if (cnt == size)
            return true;

        int ty = y + list.get(cnt).y;
        int tx = x + list.get(cnt).x;

        if (ty >= 0 && tx >= 0 && ty < N && tx < M && !map[ty][tx]) {
            map[ty][tx] = true;

            if (dfs(y, x, cnt + 1, size))
                return true;

            map[ty][tx] = false;
        }

        return false;
    }

    public static void savePoint() {
        list = new ArrayList<>();

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (sticker[y][x] == 1) {
                    list.add(new Pos(y, x));
                }
            }
        }
    }

    public static void rotate() {
        int tR = C, tC = R;
        int[][] tmp = new int[tR][tC];

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                tmp[x][R - y - 1] = sticker[y][x];
            }
        }

        sticker = tmp;
        R = tR;
        C = tC;
    }

    public static void print() {
        for (int y = 0; y < R; y++) {
            System.out.println(Arrays.toString(sticker[y]));
        }
        System.out.println();
    }
}