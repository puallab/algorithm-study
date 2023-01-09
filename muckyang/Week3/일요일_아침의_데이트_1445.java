package com.mrcAlgo.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 일요일_아침의_데이트_1445 {
    static int N, M;
    static boolean[][] visit;
    static int[][] map; // S : 시작(0), F: 끝(0) , g : 쓰레기(2) , 쓰레기옆 (1)
    static PriorityQueue<Point> pq;
    static int sx, sy, ex, ey;

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    private static class Point implements Comparable<Point> {
        int y, x;
        int nearGarCnt, garCnt; // nearGar : 쓰레기 주변 지남 , gar : 쓰레기 지남

        public Point(int y, int x, int nearGarCnt, int garCnt) {
            this.y = y;
            this.x = x;
            this.nearGarCnt = nearGarCnt;
            this.garCnt = garCnt;
        }

        @Override
        public int compareTo(Point p) {
            if (this.garCnt == p.garCnt)
                return this.nearGarCnt - p.nearGarCnt;
            return this.garCnt - p.garCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        pq = new PriorityQueue<>();
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if (c == 'g')
                    map[i][j] = 2;
                if (c == 'S') {
                    sy = i;
                    sx = j;
                }
                if (c == 'F') {
                    ey = i;
                    ex = j;
                }
            }
        }

        //쓰레기 주변 설정
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    for (int d = 0; d < 4; d++) {
                        int ty = dy[d] + i;
                        int tx = dx[d] + j;
                        if (ty < 0 || tx < 0 || ty >= N || tx >= M || map[ty][tx] != 0) continue;
                        if ((ty == sy &&tx== sx) || (ty == ey && tx == ex)) continue;
                        map[ty][tx] = 1;
                    }
                }
            }
        }

        pq.add(new Point(sy, sx, 0, 0));// 출발지는 계산하지 않는다
        solve();

    }

    private static void solve() {
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (visit[p.y][p.x]) // 우선순위에 밀려나서 이미 지나간 경우가 나올 수 있음
                continue;
            visit[p.y][p.x] = true;
            for (int d = 0; d < 4; d++) {
                int ty = dy[d] + p.y;
                int tx = dx[d] + p.x;
                if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[ty][tx]) continue;
                if (ey == ty && ex == tx) { // 도착지는 계산하지 않는다
                    System.out.println(p.garCnt + " " + p.nearGarCnt);
                    return;
                }

                int tNearGarCnt = p.nearGarCnt;
                int tGarCnt = p.garCnt;

                if (map[ty][tx] == 2) //쓰레기 지날때
                    tGarCnt++;
                else if (map[ty][tx] == 1) // 쓰레기 옆을 지날때
                    tNearGarCnt++;

                pq.add(new Point(ty, tx, tNearGarCnt, tGarCnt));
            }
        }
    }
}
