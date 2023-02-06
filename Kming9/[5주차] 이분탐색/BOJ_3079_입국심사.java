package Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3079_입국심사 {
    static int N, M, arr[];
    static final long MAX = 1_00_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        System.out.println(binarySearch());
    }

    private static long binarySearch() {
        long l = 0, r = M * MAX;

        while (l < r) {
            long mid = (l + r) / 2;

            if (solve(mid)) r = mid;
            else l = mid + 1;
        }

        return l;
    }

    private static boolean solve(long t) {
        long n = 0;

        for (int k : arr) {
            n += t / k;
            if (n >= M) return true;
        }

        return false;
    }
}