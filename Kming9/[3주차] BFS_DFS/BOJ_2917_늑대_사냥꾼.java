package Week3_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2917_늑대_사냥꾼 {
    static class Pos implements Comparable<Pos> {
        int y, x, d;

        public Pos(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }

        @Override
        public int compareTo(Pos o) {
            return o.d - this.d;
        }
    }

    static int N, M, map[][], ans = Integer.MAX_VALUE;
    static char origin[][];
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        origin = new char[N][];

        Queue<Pos> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        int sy = -1, sx = -1;
        for (int y = 0; y < N; y++) {
            origin[y] = br.readLine().toCharArray();
            for (int x = 0; x < M; x++) {
                if (origin[y][x] == '.') continue;
                else if (origin[y][x] == '+') {
                    q.offer(new Pos(y, x, 0));
                    visit[y][x] = true;
                } else if (origin[y][x] == 'V') {
                    sy = y;
                    sx = x;
                }
            }
        }

        Pos c;
        while (!q.isEmpty()) {
            c = q.poll();
            map[c.y][c.x] = c.d;

            for (int i = 0; i < 4; i++) {
                int ty = c.y + dy[i];
                int tx = c.x + dx[i];

                if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[ty][tx]) continue;
                q.offer(new Pos(ty, tx, c.d + 1));
                visit[ty][tx] = true;
            }
        }

        solve(new Pos(sy, sx, map[sy][sx]));
        System.out.println(ans);
    }

    private static void solve(Pos p) {
        boolean[][] visit = new boolean[N][M];
        Queue<Pos> pq = new PriorityQueue<>();
        pq.offer(p);
        visit[p.y][p.x] = true;
        ans = p.d;

        Pos c;
        while(!pq.isEmpty()){
            c = pq.poll();
            if(ans > c.d) ans = c.d;

            for (int i = 0; i < 4; i++) {
                int ty = c.y + dy[i];
                int tx = c.x + dx[i];

                if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[ty][tx]) continue;
                else if(origin[ty][tx] == 'J') {
                    if(ans > map[ty][tx])  ans = map[ty][tx];
                    return;
                }

                pq.offer(new Pos(ty, tx, map[ty][tx]));
                visit[ty][tx] = true;
            }
        }
    }
}
