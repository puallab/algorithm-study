package Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_공유기_설치 {
    static int N, C, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int l = 0, r = arr[N - 1] + 1;

        while (l < r) {
            int mid = (l + r) / 2;

            if (solve(mid)) r = mid;
            else l = mid + 1;
        }

        return l - 1;
    }

    private static boolean solve(int n) {
        int cnt = 1, min = arr[0] + n;

        for (int i = 1; i < N; i++) {
            if (arr[i] >= min) {
                if (++cnt >= C) return false;
                min = arr[i] + n;
            }
        }

        return true;
    }
}
