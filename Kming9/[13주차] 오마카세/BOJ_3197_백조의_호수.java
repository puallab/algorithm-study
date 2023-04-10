package Week12_오마카세;

import java.io.*;
import java.util.*;

public class BOJ_3197_백조의_호수 {
    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int R, C, map[][], root[];
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        Queue<Node> q = new LinkedList<>();
        int n = 2, s = 0;
        for (int y = 0; y < R; y++) {
            String row = br.readLine();
            for (int x = 0; x < C; x++) {
                if (row.charAt(x) == 'X') {
                    map[y][x] = INF;
                    continue;
                } else if (row.charAt(x) == 'L') map[y][x] = s++;
                else map[y][x] = n++;

                q.offer(new Node(y, x));
            }
        }

        init();
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node c = q.poll();

                for (int j = 0; j < 4; j++) {
                    int ty = c.y + dy[j];
                    int tx = c.x + dx[j];

                    if (ty < 0 || tx < 0 || ty >= R || tx >= C || map[ty][tx] == INF)
                        continue;

                    union(map[c.y][c.x], map[ty][tx]);
                }
                q.offer(c);
            }

            if (find(1) == 0)
                break;

            for (int i = 0; i < size; i++) {
                Node c = q.poll();

                for (int j = 0; j < 4; j++) {
                    int ty = c.y + dy[j];
                    int tx = c.x + dx[j];

                    if (ty < 0 || tx < 0 || ty >= R || tx >= C || map[ty][tx] != INF)
                        continue;

                    map[ty][tx] = map[c.y][c.x];
                    q.offer(new Node(ty, tx));
                }
            }
            ans++;
        }

        System.out.println(ans);
    }

    private static void init() {
        root = new int[R * C];
        for (int i = 1; i < root.length; i++)
            root[i] = i;
    }

    private static int find(int x) {
        if (root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;
        else if (pa < pb) root[pb] = pa;
        else root[pa] = pb;
    }
}