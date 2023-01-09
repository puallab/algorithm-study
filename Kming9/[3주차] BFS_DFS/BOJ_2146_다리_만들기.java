package Week3_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146_다리_만들기 {
    static class Pos implements Comparable<Pos>{
        int y, x, d;

        public Pos(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }

        @Override
        public int compareTo(Pos o) {
            return this.d - o.d;
        }
    }

    static int N, ans = Integer.MAX_VALUE;
    static char map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = st.nextToken().charAt(0);
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] != '1') continue;

                bfs(new Pos(y, x, 0));
            }
        }

        System.out.println(ans);
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static void bfs(Pos p) {
        boolean[][] visit = new boolean[N][N];
        Queue<Pos> q = new PriorityQueue<>();
        q.offer(p);
        map[p.y][p.x] = '2';
        visit[p.y][p.x] = true;

        Pos c;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                c = q.poll();
                if(c.d >= ans) return;

                for (int j = 0; j < 4; j++) {
                    int ty = c.y + dy[j];
                    int tx = c.x + dx[j];

                    if (ty < 0 || tx < 0 || ty >= N || tx >= N || visit[ty][tx]) continue;
                    if (map[ty][tx] == '0') q.offer(new Pos(ty, tx, c.d + 1));
                    else {
                        map[ty][tx] = '2';
                        if(c.d == 0) q.offer(new Pos(ty, tx, 0));
                        else {
                            ans = c.d;
                            return;
                        }
                    }
                    visit[ty][tx] = true;
                }
            }
        }
    }
}
