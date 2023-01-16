package Week3_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1445_일요일_아침의_데이트 {
    static class Data implements Comparable<Data> {
        int y, x, trash, side;

        public Data(int y, int x, int trash, int side) {
            this.y = y;
            this.x = x;
            this.trash = trash;
            this.side = side;
        }

        @Override
        public int compareTo(Data o) {
            return this.trash == o.trash ? this.side - o.side : this.trash - o.trash;
        }
    }

    static int N, M, sy, sx;
    static char map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 'S') {
                    sy = y;
                    sx = x;
                }
            }
        }
        bfs(new Data(sy, sx, 0, getTrash(sy, sx)));
    }

    private static void bfs(Data d) {
        boolean[][] visit = new boolean[N][M];
        Queue<Data> q = new PriorityQueue<>();
        q.offer(d);
        visit[d.y][d.x] = true;

        Data c;
        while (!q.isEmpty()) {
            c = q.poll();

            for (int i = 0; i < 4; i++) {
                int ty = c.y + dy[i];
                int tx = c.x + dx[i];

                if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[ty][tx]) continue;
                if (map[ty][tx] == 'F') {
                    System.out.println(c.trash + " " + c.side);
                    return;
                } else if (map[ty][tx] == 'g') q.offer(new Data(ty, tx, c.trash + 1, c.side));
                else q.offer(new Data(ty, tx, c.trash, c.side + getTrash(ty, tx)));

                visit[ty][tx] = true;
            }
        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static int getTrash(int y, int x) {
        if (map[y][x] != '.') return 0;

        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];

            if (ty >= 0 && tx >= 0 && ty < N && tx < M && map[ty][tx] == 'g') return 1;
        }

        return 0;
    }
}
