package Week4_투_포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_두_용액 {
    static int N, arr[], diff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        for (int n : solve())
            sb.append(n).append(" ");

        System.out.println(sb);
    }

    private static int[] solve() {
        int[] ans = new int[2];
        int l = 0, r = N - 1;

        for (; l < r; ) {
            int sum = arr[l] + arr[r];
            int abs = Math.abs(sum);

            if (abs == 0) return new int[]{arr[l], arr[r]};
            else if (abs < diff) {
                ans[0] = arr[l];
                ans[1] = arr[r];
                diff = abs;
            }

            if (sum > 0) r--;
            else l++;
        }

        return ans;
    }
}
