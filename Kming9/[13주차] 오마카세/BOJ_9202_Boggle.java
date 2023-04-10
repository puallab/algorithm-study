package Week12_오마카세;

import java.io.*;
import java.util.*;

public class BOJ_9202_Boggle {
    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N = 4, w;
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static String[] words;
    static char[][] board;
    static boolean selected, visit[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        w = Integer.parseInt(br.readLine());
        words = new String[w];
        board = new char[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < w; i++)
            words[i] = br.readLine();

        Arrays.sort(words, (o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length());
        br.readLine();

        int b = Integer.parseInt(br.readLine());
        for (int i = 0; i < b; i++) {
            for (int y = 0; y < N; y++)
                board[y] = br.readLine().toCharArray();

            int maxScore = 0, num = 0;
            String longWord = "";
            for (int j = 0; j < w; j++) {
                selected = false;
                if (!solve(j))
                    continue;

                if (words[j].length() > longWord.length())
                    longWord = words[j];
                maxScore += score[words[j].length()];
                num++;
            }

            sb.append(maxScore).append(" ").append(longWord).append(" ").append(num).append("\n");
            if (br.ready())
                br.readLine();
        }
        System.out.println(sb);
    }

    static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};

    private static boolean solve(int i) {
        char ch = words[i].charAt(0);

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (board[y][x] != ch)
                    continue;

                dfs(words[i], y, x, 1);
                if (selected)
                    return true;
            }
        }
        return false;
    }

    private static void dfs(String word, int y, int x, int idx) {
        if (selected)
            return;
        else if (idx == word.length()) {
            selected = true;
            return;
        }

        visit[y][x] = true;
        for (int i = 0; i < 8; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];

            if (ty < 0 || tx < 0 || ty >= N || tx >= N || visit[ty][tx] || board[ty][tx] != word.charAt(idx))
                continue;

            dfs(word, ty, tx, idx + 1);
        }
        visit[y][x] = false;
    }
}