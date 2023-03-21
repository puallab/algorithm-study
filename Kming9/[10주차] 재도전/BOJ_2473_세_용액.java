package Week10_재도전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473_세_용액 {
    static int N, arr[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        solve();
        System.out.println(sb);
    }

    private static void solve() {
        long ans = Long.MAX_VALUE;

        for(int i = 0; i <= N - 3; i++) {
            int findValue = -arr[i];
            int l = i + 1;
            int r = N - 1;

            while(l < r) {
                int mid = (l + r) /2;
                int sum = arr[l] + arr[r];
                long tmp = Math.abs((long)arr[i] + sum);

                if(ans > tmp) {
                    ans = tmp;
                    sb.setLength(0);
                    sb.append(arr[i]).append(" ").append(arr[l]).append(" ").append(arr[r]);
                }

                if(sum == findValue) return;
                else if(sum < findValue) l++;
                else r--;
            }
        }
    }
}
