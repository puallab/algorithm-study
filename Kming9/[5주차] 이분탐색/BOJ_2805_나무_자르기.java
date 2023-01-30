package Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805_나무_자르기 {
    static int N, M, arr[], r = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] > r) r = arr[i];
        }

        System.out.println(binarySearch(0));
    }

    private static long binarySearch(int l) {
        while (l < r) {
            int mid = (l + r) / 2;

            if (solve(mid)) r = mid;
            else l = mid + 1;
        }

        return l - 1;
    }

    private static boolean solve(int n) {
        long sum = 0;

        for (int k : arr) {
            if (k <= n) continue;

            sum += (k - n);
            if (sum >= M) return false;
        }

        return true;
    }
}
