package Week4_투_포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15961_회전_초밥 {
    static int N, d, k, c; // 접시, 초밥 가짓수, 연속해서 먹는 접시, 쿠폰 번호
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        count = new int[d + 1];

        int[] arr = new int[N + k - 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i < arr.length; i++)
            arr[i] = arr[i - N];

        int ans = 0, cnt = 0, end = k + 1;
        for (int l = 0, r = 0; r < arr.length; ) {
            if (r - l == k) {
                if (--count[arr[l++]] == 0) cnt--;
            }

            if (count[arr[r]] == 0) cnt++;
            count[arr[r++]]++;

            ans = Math.max(ans, count[c] == 0 ? cnt + 1 : cnt);
            if (ans == end) break;
        }

        System.out.println(ans);
    }
}
