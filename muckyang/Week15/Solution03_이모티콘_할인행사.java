package com.mrcAlgo.Week15_프로그래머스;

import java.util.*;

public class Solution03_이모티콘_할인행사 {

    int U, E;
    int maxSumPrice, maxEmoPlusCnt;
    int[] salePer;

    public int[] solution(int[][] users, int[] emoticons) {
        U = users.length;
        E = emoticons.length;
        salePer = new int[E];

        permu(0, users, emoticons);

        int[] answer = new int[2];

        answer[0] = maxEmoPlusCnt;
        answer[1] = maxSumPrice;

        return answer;
    }

    private void permu(int idx, int[][] users, int[] emoticons) {
        if (idx == E) {
            int sumPay = 0;
            int plusCnt = 0;
            for (int u = 0; u < U; u++) {
                int userSum = 0;
                for (int e = 0; e < E; e++) {
                    if (salePer[e] < users[u][0]) continue; // 비구매
                    int pay = emoticons[e] / 100;
                    pay *= (100 - salePer[e]);
                    userSum += pay;
                }
                if (users[u][1] <= userSum) //임티 플구매
                    plusCnt++;
                else
                    sumPay += userSum;
            }

            if (plusCnt > maxEmoPlusCnt) {
                maxEmoPlusCnt = plusCnt;
                maxSumPrice = sumPay;
            } else if (maxEmoPlusCnt == plusCnt) {
                maxSumPrice = Math.max(sumPay, maxSumPrice);
            }
            return;
        }
        for (int per = 10; per <= 40; per += 10) {
            salePer[idx] = per;
            permu(idx + 1, users, emoticons);
        }
    }
}

