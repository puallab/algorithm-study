package Week4_투_포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22862_가장_긴_짝수_연속한_부분_수열 {
    static int S, K, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[S];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int ans = 0, cnt = 0;
        for (int l = 0, r = 0; r < S;) {
            if (arr[r] % 2 == 1) {
                cnt++;
                while(cnt > K)
                    if (arr[l++] % 2 == 1) cnt--;
            }
            ans = Math.max(ans, ++r - l - cnt);
        }

        System.out.println(ans);
    }
}
