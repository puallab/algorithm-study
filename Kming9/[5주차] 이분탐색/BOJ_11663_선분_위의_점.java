package Week5_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11663_선분_위의_점 {
    static int N, M, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int li = lowerBound(l);
            int ri = upperBound(r);

            int ans = ri - li ;
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static int lowerBound(int n) {
        int l = 0, r = N;

        while (l < r) {
            int mid = (l + r) / 2;

            if (n <= arr[mid]) r = mid; // 가능
            else l = mid + 1;
        }

        return l;
    }
    private static int upperBound(int n) {
        int l = 0, r = N;

        while (l < r) {
            int mid = (l + r) / 2;

            if (n >= arr[mid]) l = mid + 1;
            else r = mid;
        }

        return l;
    }
}
