package Week6_분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14868_문명 {
    static class Data {
        int y, x;

        public Data(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N, K, map[][], roots[], cnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        roots = new int[K + 1];

        Queue<Data> q = new LinkedList<>();
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            roots[i] = i;
            map[y][x] = i;
            q.offer(new Data(y, x));
        }

        System.out.println(solve(q));
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static int solve(Queue<Data> q) {
        Data c;
        int year = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int n = 0; n < size; n++) {
                c = q.poll();

                for (int i = 0; i < 4; i++) {
                    int ty = c.y + dy[i];
                    int tx = c.x + dx[i];

                    if (ty < 0 || tx < 0 || ty >= N || tx >= N || map[ty][tx] == 0 || map[ty][tx] == map[c.y][c.x])
                        continue;
                    if (union(find(map[c.y][c.x]), find(map[ty][tx]))) return year;
                }

                q.offer(c);
            }

            for (int n = 0; n < size; n++) {
                c = q.poll();

                for (int i = 0; i < 4; i++) {
                    int ty = c.y + dy[i];
                    int tx = c.x + dx[i];

                    if (ty < 0 || tx < 0 || ty >= N || tx >= N || map[ty][tx] > 0) continue;
                    map[ty][tx] = map[c.y][c.x];
                    q.offer(new Data(ty, tx));
                }
            }
            year++;
        }

        return year;
    }

    private static int find(int x) {
        if (roots[x] == x) return x;
        return roots[x] = find(roots[x]);
    }

    private static boolean union(int pa, int pb) {
        if (pa == pb) return false;
        else if (pa < pb) roots[pb] = pa;
        else roots[pa] = pb;

        return ++cnt == K;
    }
}
