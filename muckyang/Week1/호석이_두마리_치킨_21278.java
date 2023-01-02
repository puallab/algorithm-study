package com.mrcAlgo.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 호석이_두마리_치킨_21278 {
    static int N, M; //건물 // 도로
    static LinkedList<Integer>[] hasRoad;
    static boolean[] visit;
    static Queue<Integer> q;
    static int minDist;
    static int minAnswer1;
    static int minAnswer2;
    public static void main(String[] args) throws IOException {

        q = new LinkedList<>();
        minDist = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        hasRoad = new LinkedList[N + 1];
        for (int i = 0; i <= N; i++) {
            hasRoad[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            hasRoad[to].add(from);
            hasRoad[from].add(to);
        }

        selectChikenHouse();

        System.out.println(minAnswer1 + " " + minAnswer2 + " " + minDist);
    }

    private static void selectChikenHouse() {
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                visit = new boolean[N + 1];
                q.add(i);
                q.add(j);
                visit[i] = true;
                visit[j] = true;
                int dist = fill();
//                System.out.println(i + " ,"+ j +" = " + sumDistance);
                if (minDist > dist) {
                    minDist = dist;
                    minAnswer1 = i;
                    minAnswer2 = j;
                }
            }
        }
    }

    static int fill() {
        int sumDistance= 0;
        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            dist++;
            for (int i = 0; i < size; i++) {

                int idx = q.poll();
                for (int next : hasRoad[idx]) {
                    if (!visit[next]) {
                        sumDistance += (dist * 2);
                        visit[next] = true;
                        q.add(next);
                    }
                }
            }
        }
        return sumDistance;
    }

//    static void solve() {
//        int sumDistance = 0;
//        q = new LinkedList<>();
//        visit = new boolean[N + 1];
//        visit[minAnswer1] = visit[minAnswer2] = true;
//        q.add(minAnswer1);
//        q.add(minAnswer2);
//        while (!q.isEmpty()) {
//            int size = q.size();
//            for (int i = 0; i < size; i++) {
//                int idx = q.poll();
//                for (int next : hasRoad[idx]) {
//                    if (!visit[next]) {
//                        sumDistance += 2;
//                        visit[next] = true;
//                        q.add(next);
//                    }
//                }
//            }
//        }
//        sb.append(minAnswer1).append(" ").append(minAnswer2).append(" ").append(sumDistance);
//    }
}
