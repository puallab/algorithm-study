package com.mrcAlgo.Week15_프로그래머스;

import java.util.*;

public class Solution01_개인정보_수집_유효기간 {

    public int[] solution(String today, String[] terms, String[] privacies) {

        List<Integer> list = new ArrayList<>();
        // 약관 종류, 유효 기간(일자)
        Map<String, Integer> termMap = new HashMap<>();

        String[] todaySlice = today.split("\\.");
        int ty = Integer.parseInt(todaySlice[0]);
        int tm = Integer.parseInt(todaySlice[1]);
        int td = Integer.parseInt(todaySlice[2]);

        int dayNum = dayNum(ty, tm, td);

        for (int i = 0; i < terms.length; i++) {
            String[] termSlice = terms[i].split(" ");
            termMap.put(termSlice[0], Integer.parseInt(termSlice[1]) * 28);
        }

        for (int i = 0; i < privacies.length; i++) {

            String[] priSlice = privacies[i].split(" ");
            String[] inDate = priSlice[0].split("\\.");

            int y = Integer.parseInt(inDate[0]);
            int m = Integer.parseInt(inDate[1]);
            int d = Integer.parseInt(inDate[2]);

            int endDay = dayNum(y, m, d) + termMap.get(priSlice[1]);
            if (endDay <= dayNum)
                list.add(i + 1);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    private int dayNum(int y, int m, int d) {
        return d + 28 * (m + 12 * y);
    }
}
