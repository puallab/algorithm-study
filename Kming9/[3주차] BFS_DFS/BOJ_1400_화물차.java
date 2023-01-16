package Week3_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1400_화물차 {
    static class Data implements Comparable<Data> {
        int y, x, dir, t;

        public Data(int y, int x, int dir, int t) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.t = t;
        }

        @Override
        public int compareTo(Data o) {
            return this.t - o.t;
        }
    }

    static int N, M, map[][], times[][];
    static char tmp[][];
    static boolean visit[][];
    static Data info[];
    static ArrayList<Data> list;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            Queue<Data> q = new PriorityQueue<>();
            Queue<Data> pq = new PriorityQueue<>();
            list = new ArrayList<>();
            map = new int[N][M];
            tmp = new char[N][M];
            visit = new boolean[N][M];

            for (int y = 0; y < N; y++) {
                tmp[y] = br.readLine().toCharArray();
                for (int x = 0; x < M; x++) {
                    if (tmp[y][x] == 'A') {
                        q.offer(new Data(y, x, 0, 1));
                        map[y][x] = -1;
                        visit[y][x] = true;
                    } else if (tmp[y][x] == '.') map[y][x] = -1;
                    else if (tmp[y][x] >= '0' && tmp[y][x] <= '9') list.add(new Data(y, x, tmp[y][x] - '0', -1));
                    else map[y][x] = 3;
                }
            }

            init();
            times = new int[info.length][2];

            Data c, d;
            for (int i = 0; i < info.length; i++) {
                st = new StringTokenizer(br.readLine());

                int idx = Integer.parseInt(st.nextToken());
                info[idx].dir = st.nextToken().charAt(0) == '-' ? 0 : 1;

                for (int j = 0; j < 2; j++)
                    times[idx][j] = Integer.parseInt(st.nextToken());

                c = info[idx];
                map[c.y][c.x] = info[idx].dir;
                pq.offer(new Data(c.y, c.x, map[c.y][c.x], 1));
            }

            st = new StringTokenizer(br.readLine());

            int ans = -1;
            out:while (!q.isEmpty()) {
                c = q.poll();

                int time = c.t;
                while (!pq.isEmpty() && pq.peek().t == time) {
                    d = pq.poll();
                    map[d.y][d.x] = d.dir % 2;
                    pq.offer(new Data(d.y, d.x, d.dir + 1, d.t + times[tmp[d.y][d.x] - '0'][map[d.y][d.x]]));
                }

                for (int i = 0; i < 4; i++) {
                    int ty = c.y + dy[i];
                    int tx = c.x + dx[i];

                    if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[ty][tx] || map[ty][tx] < 0) continue;
                    else if(tmp[ty][tx] == 'B') {
                        ans = c.t;
                        break out;
                    }
                    else if (map[ty][tx] == 3 || i % 2 == map[ty][tx]) {
                        q.offer(new Data(ty, tx, 0, c.t + 1));
                        visit[ty][tx] = true;
                    }
                    else  q.offer(new Data(c.y, c.x, 0, pq.peek().t));
                }
            }

            sb.append(ans < 0 ? "impossible" : ans).append("\n");
        }

        System.out.println(sb);
    }

    private static void init() {
        info = new Data[list.size()];
        for (Data d : list)
            info[d.dir] = new Data(d.y, d.x, -1, -1);
    }
}