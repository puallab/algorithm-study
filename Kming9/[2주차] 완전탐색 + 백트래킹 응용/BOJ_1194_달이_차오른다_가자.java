package Week2_백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_달이_차오른다_가자 {
    private static class Data {
        int y;
        int x;
        int keys;
        int cnt;

        public Data(int y, int x, int keys, int cnt) {
            this.y = y;
            this.x = x;
            this.keys = keys;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static char map[][];
    static boolean visit[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[64][N][M];

        int sy = -1, sx = -1;
        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
            for (int x = 0; x < M; x++) {
                if (map[y][x] == '0') {
                    sy = y;
                    sx = x;
                }
            }
        }

        System.out.println(bfs(sy, sx));
    }

    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};

    public static int bfs(int y, int x) {
        Queue<Data> q = new LinkedList<>();
        q.offer(new Data(y, x, 0, 1));
        visit[0][y][x] = true;

        Data d;
        int tk;
        while (!q.isEmpty()) {
            d = q.poll();

            for (int i = 0; i < 4; i++) {
                int ty = d.y + dy[i];
                int tx = d.x + dx[i];
                tk = d.keys;

                if (ty < 0 || tx < 0 || ty >= N || tx >= M || map[ty][tx] == '#' || visit[d.keys][ty][tx]) continue;
                else if (map[ty][tx] == '1') return d.cnt;
                else if (map[ty][tx] >= 'a' && map[ty][tx] <= 'f')
                    tk = d.keys | (1 << map[ty][tx] - 'a');
                else if (map[ty][tx] >= 'A' && map[ty][tx] <= 'F') {
                    if ((d.keys & (1 << map[ty][tx] - 'A')) <= 0) continue;
                }

                visit[d.keys][ty][tx] = true;
                q.offer(new Data(ty, tx, tk, d.cnt + 1));
            }
        }

        return -1;
    }
}

