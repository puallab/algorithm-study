package Week3_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1525_퍼즐 {
    static class Data {
        int y, x;
        String status;

        public Data(int y, int x, String status) {
            this.y = y;
            this.x = x;
            this.status = status;
        }
    }

    static final String INIT_VAL = "123456780";
    static int map[][], N = 3;
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int sy = -1, sx = -1, val = 0;
        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 0) {
                    sy = y;
                    sx = x;
                }
                val = val * 10 + map[y][x];
            }
        }

        System.out.println(bfs(new Data(sy, sx, String.valueOf(val))));
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static int bfs(Data d) {
        if (INIT_VAL.equals(d.status)) return 0;

        Queue<Data> q = new LinkedList<>();
        q.offer(d);
        set.add(d.status);

        int cnt = 0;
        Data c;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;

            for (int i = 0; i < size; i++) {
                c = q.poll();

                for (int j = 0; j < 4; j++) {
                    int ty = c.y + dy[j];
                    int tx = c.x + dx[j];

                    if (ty < 0 || tx < 0 || ty >= N || tx >= N) continue;
                    String next = getStatus(c.y, c.x, ty, tx, c.status);
                    if (INIT_VAL.equals(next)) return cnt;
                    else if (set.add(next)) q.offer(new Data(ty, tx, next));
                }
            }
        }

        return -1;
    }

    private static String getStatus(int y, int x, int ty, int tx, String status) {
        char[] crr = status.toCharArray();

        int oi = y * 3 + x;
        int ri = ty * 3 + tx;
        char tmp = crr[oi];
        crr[oi] = crr[ri];
        crr[ri] = tmp;

        return String.valueOf(crr);
    }
}
