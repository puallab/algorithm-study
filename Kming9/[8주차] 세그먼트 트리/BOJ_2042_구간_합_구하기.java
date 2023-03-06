package Week8_세그먼트_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042_구간_합_구하기 {
    static int N, M, K;
    static long arr[], tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        tree = new long[4 * N];

        for (int i = 1; i <= N; i++)
            arr[i] = Long.parseLong(br.readLine());

        init(1, N, 1);
        for (int i = M + K; i > 0; i--) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) update(1, N, 1, b, c);
            else sb.append(query(1, N, 1, b, c)).append("\n");
        }
        System.out.print(sb);
    }

    public static long init(int l, int r, int node) {
        if (l == r) return tree[node] = arr[l];

        int mid = (l + r) / 2;
        return tree[node] = init(l, mid, node * 2) + init(mid + 1, r, node * 2 + 1);
    }

    public static long update(int l, int r, int node, int idx, long val) {
        if (idx < l || idx > r) return tree[node];
        else if (l == r) return tree[node] = val;

        int mid = (l + r) / 2;
        return tree[node] = update(l, mid, node * 2, idx, val) + update(mid + 1, r, node * 2 + 1, idx, val);
    }

    public static long query(int l, int r, int node, int ql, long qr) {
        if (ql > r || qr < l) return 0;
        else if(ql <= l && r <= qr) return tree[node];

        int mid = (l + r) / 2;
        return query(l, mid, node * 2, ql, qr) + query(mid + 1, r, node * 2 + 1, ql, qr);
    }
}