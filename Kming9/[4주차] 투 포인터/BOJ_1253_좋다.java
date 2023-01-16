package Week4_투_포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_좋다 {
    static int N, arr[], ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        for (int i = 0; i < N; i++) solve(i);
        System.out.println(ans);
    }

    private static void solve(int i) {
        int n = arr[i];
        for (int l = 0, r = N - 1; l < r; ) {
            if (l == i) l++;
            else if (r == i) r--;
            else {
                int sum = arr[l] + arr[r];
                if (sum == n) {
                    ans++;
                    return;
                } else if (sum < n) l++;
                else r--;
            }
        }
    }
}
