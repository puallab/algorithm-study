import java.util.*;

class Solution {
    int num, price, N;
    int[] sale = {10, 20, 30, 40};
    public int[] solution(int[][] users, int[] emoticons) {
        num = -1;
        price = -1;
        N = emoticons.length;
        dfs(users, emoticons, new int[N], 0, 0);
        return new int[]{num, price};
    }
    
    public void dfs(int[][] users, int[] emoticons, int[] picked, int idx, int cnt) {
        if (cnt == N) {
            solve(users, emoticons, picked);
            return;
        }

        for (int i = 0; i < sale.length; i++) {
            picked[cnt] = i;
            dfs(users, emoticons, picked, i + 1, cnt + 1);
        }
    }

    public void solve(int[][] users, int[] emoticons, int[] picked) {
        int cnt = 0, sum = 0;

        for (int i = 0; i < users.length; i++) {
            int p = 0;

            for (int j = 0; j < emoticons.length; j++) {
                if (users[i][0] <= sale[picked[j]])
                    p += emoticons[j] * (100 - sale[picked[j]]) / 100;
            }

            if (p >= users[i][1])
                cnt++;
            else
                sum += p;
        }

        if (num < cnt) {
            num = cnt;
            price = sum;
        } else if (num == cnt && price < sum) {
            price = sum;
        }
    }
}