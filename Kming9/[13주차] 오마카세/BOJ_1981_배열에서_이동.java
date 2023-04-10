package Week12_오마카세;

import java.io.*;
import java.util.*;

public class BOJ_1981_배열에서_이동 {
    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N, target, map[][];
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        target = N - 1;
        map = new int[N][N];

        int min = 200, max = 0;
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                min = Math.min(map[y][x], min);
                max = Math.max(map[y][x], max);
            }
        }

        System.out.println(binarySearch(max - min));
    }

    private static int binarySearch(int r) {
        int l = 0;

        while (l < r) {
            int mid = (l + r) / 2;

            if (solve(mid)) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    static int min, max;

    private static boolean solve(int diff) {
        for (min = map[0][0], max = map[0][0] + diff; min >= 0 && max >= map[0][0]; min--, max--) {
            if (bfs(0, 0))
                return true;
        }
        return false;
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static boolean bfs(int y, int x) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(y, x));
        visit = new boolean[N][N];
        visit[y][x] = true;

        while (!q.isEmpty()) {
            Node c = q.poll();

            for (int i = 0; i < 4; i++) {
                int ty = c.y + dy[i];
                int tx = c.x + dx[i];

                if (ty < 0 || tx < 0 || ty >= N || tx >= N || visit[ty][tx] || map[ty][tx] < min || map[ty][tx] > max)
                    continue;
                else if (ty == target && tx == target)
                    return true;

                q.offer(new Node(ty, tx));
                visit[ty][tx] = true;
            }
        }

        return false;
    }
}