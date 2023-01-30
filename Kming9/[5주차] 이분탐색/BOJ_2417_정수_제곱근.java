package Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2417_정수_제곱근 {
    static long n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        System.out.println(binarySearch());
    }

    private static long binarySearch() {
        long l = 0, r = n;

        while (l < r) {
            long mid = (l + r) / 2;
            double sqr = (double) mid * mid;

            if (sqr == n) return mid;
            else if (sqr < n) l = mid + 1;
            else r = mid;
        }

        return r;
    }
}
