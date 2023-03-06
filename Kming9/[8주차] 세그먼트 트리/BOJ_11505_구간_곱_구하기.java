package Week8_세그먼트_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11505_구간_곱_구하기 {
    static int N, M, K, arr[];
    static long tree[];
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
//        System.out.println((MOD-1L) * (MOD-1));
//        System.out.println(Integer.MAX_VALUE);

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        tree = new long[4 * N];

        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        init(1, N, 1);
        for (int i = M + K; i > 0; i--) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) update(1, N, 1, b, c);
            else sb.append(query(1, N, 1, b, c)).append("\n");
        }
        System.out.print(sb);
    }

    public static long init(int l, int r, int node) {
        if (l == r) return tree[node] = arr[l];

        int mid = (l + r) / 2;
        long left = init(l, mid, node * 2) % MOD;
        long right = init(mid + 1, r, node * 2 + 1) % MOD;
        return tree[node] = (left * right) % MOD;
    }

    public static long update(int l, int r, int node, int idx, int val) {
        if (idx < l || idx > r) return tree[node];
        else if (l == r) return tree[node] = val;

        int mid = (l + r) / 2;
        long left = update(l, mid, node * 2, idx, val) % MOD;
        long right = update(mid + 1, r, node * 2 + 1, idx, val) % MOD;
        return tree[node] = (left * right) % MOD;
    }

    public static long query(int l, int r, int node, int ql, int qr) {
        if (ql > r || qr < l) return 1;
        else if (ql <= l && r <= qr) return tree[node];

        int mid = (l + r) / 2;
        long left = query(l, mid, node * 2, ql, qr) % MOD;
        long right = query(mid + 1, r, node * 2 + 1, ql, qr) % MOD;
        return (left * right) % MOD;
    }
}