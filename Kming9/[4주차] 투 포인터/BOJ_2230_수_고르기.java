package Week4_투_포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230_수_고르기 {
    static int N, M, arr[], diff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        System.out.println(solve());
    }

    private static int solve() {
        int l = 0, r = 0;

        while (l < N && r < N) {
            int sum = arr[r] - arr[l];
            int abs = Math.abs(sum);

            if (abs == M) return M;
            else if (abs < M) r++;
            else {
                if (abs < diff) diff = abs;
                l++;
            }
        }

        return diff;
    }
}