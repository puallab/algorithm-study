package Week4_투_포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11728_배열_합치기 {
    static int N, M, arrA[], arrB[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arrA = new int[N];
        arrB = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arrA[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            arrB[i] = Integer.parseInt(st.nextToken());

        for (int n : solve())
            sb.append(n).append(" ");

        System.out.println(sb);
    }

    private static int[] solve() {
        int[] res = new int[N + M];

        int l = 0, r = 0, i = 0;

        while (l < N && r < M) {
            if (arrA[l] <= arrB[r]) res[i++] = arrA[l++];
            else res[i++] = arrB[r++];
        }

        while (l < N) res[i++] = arrA[l++];
        while (r < M) res[i++] = arrB[r++];

        return res;
    }
}
