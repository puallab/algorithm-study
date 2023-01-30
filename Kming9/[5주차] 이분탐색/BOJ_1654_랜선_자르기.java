package Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654_랜선_자르기 {
    static int K, N, arr[];
    static long r = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[K];

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (arr[i] > r) r = arr[i];
        }

        ++r;
        System.out.println(binarySearch(1));
    }

    private static long binarySearch(long l) {
        while (l < r) {
            long mid = (l + r) / 2; // 2147483647

            if (solve(mid)) l = mid + 1;
            else r = mid;
        }

        return l - 1;
    }

    private static boolean solve(long n) {
        long sum = 0;

        for (int k : arr) {
            sum += k / n;
            if (sum >= N) return true;
        }

        return false;
    }
}
