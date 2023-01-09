package com.mrcAlgo.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 화물차_1400 {
    static StringBuilder sb;
    static int N, M, K;
    static int[][] map;
    static int[][] trafficLight;
    static int[] lightDefault; // 신호등 초기상태
    static boolean[][] visit;

    static PriorityQueue<Point> pq;

    static int[] dy = {0, -1, 0, 1}; // 서 북 동 남
    static int[] dx = {-1, 0, 1, 0}; //

    static int sy, sx, ey, ex;

    private static class Point implements Comparable<Point> {
        int y, x, time;

        public Point(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }


        @Override
        public int compareTo(Point p) { // 짧은시간 우선순위
            return this.time - p.time;
        }
    }

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = 0;
            if (N == 0 && M == 0)
                break;

            map = new int[N][M];
            visit = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = s.charAt(j);
                    if (c == '#') continue;
                    if (c == '.') {
                        map[i][j] = -1;
                    } else if (c == 'A') {
                        sy = i;
                        sx = j;
                    } else if (c == 'B') {
                        ey = i;
                        ex = j;
                    } else {
                        K++;
                        int lightNum = (c - '0');
                        map[i][j] = lightNum + 100;// 신호등은 100 ~ 109
                    }
                }
            }
            trafficLight = new int[3][K]; // 0 : 동서신호 시간 , 1:남북신호 시간
            lightDefault = new int[K];

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                if (st.nextToken().equals("|")) {
                    lightDefault[num] = 1; // 0 -> 초기상태 :'-', 1 -> 초기상태 : '|'
                }
                int dongSeo = Integer.parseInt(st.nextToken());
                int namBuk = Integer.parseInt(st.nextToken());
                trafficLight[0][k] = dongSeo;
                trafficLight[1][k] = namBuk;
                trafficLight[2][k] = dongSeo + namBuk;
            }
            pq = new PriorityQueue<>();
            pq.add(new Point(sy, sx, 0));
            visit[sy][sx] = true;
            solve();
            br.readLine();
        }
        System.out.println(sb);
    }

    private static void solve() {
        int time = 0;
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int s = 0; s < size; s++) {
                Point p = pq.poll();
                for (int d = 0; d < 4; d++) {
                    int ty = p.y + dy[d];
                    int tx = p.x + dx[d];

                    if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[ty][tx] || map[ty][tx] == -1) continue;
                    if (ty == ey && tx == ex) { //도착 지점
                        sb.append(time + 1).append("\n");
                        return;
                    }

                    if (map[ty][tx] >= 100) { // 신호등
                        int lightNum = map[ty][tx] - 100;
                        if (!greenLight(lightNum, d, time)) { // 초록불이 아님 -> 이동 불가
                            pq.add(new Point(p.y, p.x, time + 1)); // p좌표 (현재좌표) 그대로 넣어주고 시간만 증가
                            continue;
                        }
                    }

                    //이동
                    visit[ty][tx] = true;
                    pq.add(new Point(ty, tx, time + 1));
                }
            }
            time++;
        }
        // 도착지로 이동불가
        sb.append("impossible").append("\n");
    }

    private static boolean greenLight(int lNum, int d, int time) {
        int go = d % 2;// 0 : 동서 , 1 : 남북
        int sLight = lightDefault[lNum]; // 신호등 초기상태 0(-) or 1(|)
        int per = time % trafficLight[2][lNum]; // 신호등 한 사이클로 현재 시간 % 연산 //// 한사이클 = 초록불 시간 + 빨간불 시간
        if (sLight == go) { // 신호등의 초기상태와 이동방향이 같은 방향이라면
            if (per < trafficLight[sLight][lNum]) // light[sLight][lNum] : 초기상태의 신호시간
                return true;
            return false;
        } else { // 신호등의 초기 상태와 이동방향이 다르다면,
            if (per < trafficLight[sLight][lNum])
                return false;
            return true;
        }
    }
}
