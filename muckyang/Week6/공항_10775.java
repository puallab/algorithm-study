package com.mrcAlgo.Week6_유니온파인드;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 공항_10775 {

    static int G, P;
    static int[] group;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        group = new int[G + 1];
        int cnt = 0;

        for (int i = 1; i <= G; i++)
            group[i] = i;

        for (int i = 0; i < P; i++) {
            int airPlaneIdx = Integer.parseInt(br.readLine());
            if (canDocking(airPlaneIdx)) cnt++;
            else break;
        }
        System.out.println(cnt);
    }

    private static boolean canDocking(int dockIdx) {
        int findIdx = find(dockIdx);
        if (findIdx > 0) {

            group[findIdx] = find(findIdx - 1);
            return true;
        }
        return false;
    }

    private static int find(int idx) {
        if (group[idx] == idx)
            return idx;
        return group[idx] = find(group[idx]);
    }
}
