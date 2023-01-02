package Week2_백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_21277_짠돌이_호석 {
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

    static int N, M, basePos[], ans = Integer.MAX_VALUE;
    static char puzzles[][][], map[][], puzzle[][];
    static ArrayList<Pos> lists[], positions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        lists = new ArrayList[2];
        puzzles = new char[2][][];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            puzzles[i] = new char[N][M];
            lists[i] = new ArrayList<>();

            for (int y = 0; y < N; y++) {
                puzzles[i][y] = br.readLine().toCharArray();
                for (int x = 0; x < M; x++) {
                    if (puzzles[i][y][x] == '1')
                        lists[i].add(new Pos(y, x));
                }
            }
        }

        initMap();
        solve();

        System.out.println(ans);
    }

    public static void solve() {
        int size;

        for (int i = 0; i < 4; i++) {
            int R = puzzle.length;
            int C = puzzle[0].length;

            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    size = getSize(new int[]{y, y + puzzle.length, x, x + puzzle[0].length});

                    if (size >= ans) continue;
                    if (dfs(y, x, 0)) {
                        ans = size;
                    }
                }
            }

            if (i == 3) break;
            rotation();
        }
    }

    public static void rotation() {
        int R = puzzle.length, C = puzzle[0].length;
        char tmp[][] = new char[C][R];
        ArrayList<Pos> tList = new ArrayList<>();

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                tmp[x][R - y - 1] = puzzle[y][x];
                if (puzzle[y][x] == '1')
                    tList.add(new Pos(x, R - y - 1));
            }
        }

        puzzle = tmp;
        positions = tList;
    }

    public static boolean dfs(int y, int x, int i) {
        boolean isPossible = false;
        if (i == positions.size()) {
            return true;
        }

        int ty = y + positions.get(i).y;
        int tx = x + positions.get(i).x;

        if (ty < 0 || tx < 0 || ty >= map.length || tx >= map[0].length || map[ty][tx] == '1')
            return false;

        map[ty][tx] = '1';
        isPossible = dfs(y, x, i + 1);
        map[ty][tx] = '\u0000';

        return isPossible;
    }

    public static int getSize(int[] p) {
        int tmp[], size = 1;

        for (int i = 0; i < 4; i += 2) {
            tmp = new int[]{p[i], p[i + 1], basePos[i], basePos[i + 1]};
            Arrays.sort(tmp);
            size *= (tmp[3] - tmp[0]);
        }

        return size;
    }

    public static void initMap() {
        int baseIdx = lists[0].size() >= lists[1].size() ? 0 : 1;
        puzzle = baseIdx == 0 ? puzzles[1] : puzzles[0];
        positions = baseIdx == 0 ? lists[1] : lists[0];

        int maxLen = Math.max(puzzle.length, puzzle[0].length);
        int bR = puzzles[baseIdx].length + maxLen;
        int bC = puzzles[baseIdx][0].length + maxLen;
        basePos = new int[]{maxLen, bR, maxLen, bC};

        map = new char[bR + maxLen][bC + maxLen];
        for (int y = maxLen; y < bR; y++) {
            for (int x = maxLen; x < bC; x++) {
                map[y][x] = puzzles[baseIdx][y - maxLen][x - maxLen];
            }
        }
    }
}
