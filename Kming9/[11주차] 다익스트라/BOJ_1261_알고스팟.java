package Week11_다익스트라;

import java.io.*;
import java.util.*;

public class BOJ_1261_알고스팟 {
    static class Node implements Comparable<Node> {
        int y, x, v;

        public Node(int y, int x, int v) {
            this.y = y;
            this.x = x;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N, M, map[][], visit[][];
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new int[N][M];

        for (int y = 0; y < N; y++) {
            String row = br.readLine();
            for (int x = 0; x < M; x++) {
                map[y][x] = row.charAt(x) - '0';
                visit[y][x] = INF;
            }
        }

        N--;
        M--;
        System.out.println(solve(0, 0));
    }

    private static int solve(int y, int x) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(y, x, 0));
        visit[y][x] = 0;

        Node c;
        while (!pq.isEmpty()) {
            c = pq.poll();

            if (c.y == N && c.x == M)
                return c.v;

            for (int i = 0; i < 4; i++) {
                int ty = c.y + dy[i];
                int tx = c.x + dx[i];

                if (ty < 0 || tx < 0 || ty > N || tx > M) continue;

                int next = c.v + map[ty][tx];
                if(visit[ty][tx] > next) {
                    pq.offer(new Node(ty, tx, next));
                    visit[ty][tx] = next;
                }
            }
        }

        return -1;
    }
}