package com.mrcAlgo.Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 퍼즐_1525 {
    static int[] map;
    static int[] powNum;
    static boolean[][] road;
    static HashSet<Integer> hs;
    static Queue<Puzzle> q;
    static int defaultNumber;

    static int res;

    private static class Puzzle {
        int number, zeroIdx;

        public Puzzle(int number, int zeroIdx) {
            this.number = number;
            this.zeroIdx = zeroIdx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        res = -1;
        map = new int[9];
        powNum = new int[9];
        road = new boolean[4][9];
        hs = new HashSet<>();
        q = new LinkedList<>();
        initRoad();

        int zeroIdx = -1;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i * 3 + j] = Integer.parseInt(st.nextToken());
                if (map[i * 3 + j] == 0) {
                    zeroIdx = i * 3 + j;
                }
            }
        }

        int nowNumber = getMapNumber();
//        int nowNumber = 0;
        defaultNumber = 0;
        for (int i = 0; i < 9; i++) {
//            nowNumber *= 10; /// 0
//            nowNumber += map[i];
            defaultNumber *= 10;
            defaultNumber += i;
            powNum [i] = (int) Math.pow(10, 8 - i);
        }
        if (nowNumber == defaultNumber) {
            System.out.println(0);
        } else {
            hs.add(defaultNumber);
            q.add(new Puzzle(nowNumber, zeroIdx));
            bfs();
            System.out.println(res);
        }
    }

    private static void bfs() {
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            for (int s = 0; s < size; s++) {
                Puzzle p = q.poll();

                for (int d = 0; d < 4; d++) {
                    if (road[d][p.zeroIdx]) {
                        int nextZeroIdx = getNextZeroIdx(d, p.zeroIdx);
                        int nextNumber = getNextNumber(p.number, p.zeroIdx, nextZeroIdx);
                        if (nextNumber == defaultNumber) {
                            res = cnt;
                            return;
                        }
                        if (hs.contains(nextNumber)) {
                            continue;
                        }
                        hs.add(nextNumber);
                        q.add(new Puzzle(nextNumber, nextZeroIdx));
                    }
                }
            }
        }
    }

    private static int getNextNumber(int number, int zeroIdx, int nextZeroIdx) {
        return number - (number / powNum[nextZeroIdx] % 10 * powNum[nextZeroIdx])
                + (number / powNum[nextZeroIdx] % 10 * powNum[zeroIdx]);
    }

    private static int getNextZeroIdx(int d, int zeroIdx) {
        int nextZeroIdx = 0;
        if (d == 0) { //>>>>
            nextZeroIdx = zeroIdx + 1;
        } else if (d == 1) { // <<<<<
            nextZeroIdx = zeroIdx - 1;
        } else if (d == 2) { //아래
            nextZeroIdx = zeroIdx + 3;
        } else if (d == 3) { //위
            nextZeroIdx = zeroIdx - 3;
        }

        return nextZeroIdx;
    }

    private static int getMapNumber() {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            if (map[i] == 0) {
                result = Integer.parseInt(result + "0");
            } else
                result = result * 10 + map[i];
        }
        return result;
    }

    private static void initRoad() {
        for (int d = 0; d < 4; d++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (d == 0) { // >>>>
                        if (j < 2) {
                            road[d][i * 3 + j] = true;
                        }
                    } else if (d == 1) { // <<<<<
                        if (0 < j) {
                            road[d][i * 3 + j] = true;
                        }
                    } else if (d == 2) { // 아래
                        if (i < 2) {
                            road[d][i * 3 + j] = true;
                        }
                    } else if (d == 3) { // 위
                        if (0 < i) {
                            road[d][i * 3 + j] = true;
                        }
                    }
                }
            }
        }
    }
}
