package Week3_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16988_Baaaaaaaaaduk2_Easy {
    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N, M, ans = 0;
    static char map[][];
    static ArrayList<Pos> empty = new ArrayList<>(), group = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = st.nextToken().charAt(0);
                if (map[y][x] == '0') empty.add(new Pos(y, x));
            }
        }

        findGroup();
        combination(0, 0);
        System.out.println(ans);
    }

    private static void combination(int idx, int cnt) {
        if (cnt == 2) {
            check();
            return;
        }

        for (int i = idx; i < empty.size(); i++) {
            Pos p = empty.get(i);
            map[p.y][p.x] = '1';
            combination(i + 1, cnt + 1);
            map[p.y][p.x] = '0';
        }
    }

    private static void check() {
        int n = 0;
        boolean[][] visit = new boolean[N][M];

        for (Pos p : group)
            n += bfs(p.y, p.x, visit);

        if (ans < n) ans = n;
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static void findGroup() {
        boolean[][] visit = new boolean[N][M];
        Queue<Pos> q = new LinkedList<>();
        Pos c;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] != '2' || visit[y][x]) continue;
                group.add(new Pos(y, x));
                q.offer(new Pos(y, x));
                visit[y][x] = true;

                while (!q.isEmpty()) {
                    c = q.poll();

                    for (int i = 0; i < 4; i++) {
                        int ty = c.y + dy[i];
                        int tx = c.x + dx[i];

                        if (ty < 0 || tx < 0 || ty >= N || tx >= M || map[ty][tx] != '2' || visit[ty][tx]) continue;
                        q.offer(new Pos(ty, tx));
                        visit[ty][tx] = true;
                    }
                }
            }
        }
    }

    private static int bfs(int y, int x, boolean[][] visit) {
        int n = 1;
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(y, x));
        visit[y][x] = true;

        Pos c;
        while (!q.isEmpty()) {
            c = q.poll();

            for (int i = 0; i < 4; i++) {
                int ty = c.y + dy[i];
                int tx = c.x + dx[i];

                if (ty < 0 || tx < 0 || ty >= N || tx >= M || map[ty][tx] == '1' || visit[ty][tx]) continue;
                else if (map[ty][tx] == '0') return 0;

                q.offer(new Pos(ty, tx));
                visit[ty][tx] = true;
                n++;
            }
        }

        return n;
    }
}