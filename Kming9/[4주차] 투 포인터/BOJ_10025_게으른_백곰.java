package Week4_투_포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10025_게으른_백곰 {
    static int N, K, pos[];
    static long ans;
    static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) * 2 + 1;
        pos = new int[MAX + 1];

        int g, x;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            g = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            pos[x] = g;
        }

        int r = 0, cnt = 0;
        long sum = 0;
        while (cnt++ < K) {
            sum += pos[r++];
            if(r > MAX) break;
        }

        ans = sum;

        for (int l = 0; r <= MAX; l++, r++) {
            sum = sum - pos[l] + pos[r];
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}
