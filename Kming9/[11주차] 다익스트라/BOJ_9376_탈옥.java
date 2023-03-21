package Week11_다익스트라;

import java.io.*;
import java.util.*;

public class BOJ_9376_탈옥 {
    static class Node implements Comparable<Node> {
        int y, x, c;

        public Node(int y, int x, int c) {
            this.y = y;
            this.x = x;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int h, w, cost[][][], minPath[] = new int[3];
    static char map[][];
    static PriorityQueue<Node>[] pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            Arrays.fill(minPath, INF);
            map = new char[h][w];
            cost = new int[3][h][w];
            pq = new PriorityQueue[3];
            for (int j = 0; j < 3; j++) {
                pq[j] = new PriorityQueue<>();
                for (int y = 0; y < h; y++)
                    Arrays.fill(cost[j][y], INF);
            }

            int num = 0;
            for (int y = 0; y < h; y++) {
                String row = br.readLine();
                for (int x = 0; x < w; x++) {
                    map[y][x] = row.charAt(x);

                    if (map[y][x] == '*')
                        continue;
                    else if (y == 0 || x == 0 || y == h - 1 || x == w - 1) {
                        cost[0][y][x] = (map[y][x] == '#') ? 1 : 0;
                        pq[0].add(new Node(y, x, cost[0][y][x]));
                    }

                    if (map[y][x] == '$') {
                        cost[++num][y][x] = 0;
                        pq[num].add(new Node(y, x, 0));
                    }
                }
            }

            for (int j = 0; j < 3; j++)
                dijkstra(j);

            int min = Integer.MAX_VALUE;
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (cost[0][y][x] == INF || cost[1][y][x] == INF || cost[2][y][x] == INF)
                        continue;

                    int sum = cost[0][y][x] + cost[1][y][x] + cost[2][y][x];
                    min = Math.min(min, map[y][x] == '#' ? sum - 2 : sum);
                }
            }
            sb.append(Math.min(min, minPath[1] + minPath[2])).append("\n");
        }
        System.out.println(sb);
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static void dijkstra(int idx) {
        while (!pq[idx].isEmpty()) {
            Node n = pq[idx].poll();

            for (int i = 0; i < 4; i++) {
                int ty = n.y + dy[i];
                int tx = n.x + dx[i];

                if (ty < 0 || tx < 0 || ty >= h || tx >= w) {
                    minPath[idx] = Math.min(minPath[idx], cost[idx][n.y][n.x]);
                    continue;
                } else if (map[ty][tx] == '*')
                    continue;

                int nextCost = map[ty][tx] == '#' ? n.c + 1 : n.c;
                if (nextCost >= cost[idx][ty][tx])
                    continue;

                pq[idx].offer(new Node(ty, tx, nextCost));
                cost[idx][ty][tx] = nextCost;
            }
        }
    }
}

/*
1
3 5
*****
#$∗$#
*****

정답 : 2
 */