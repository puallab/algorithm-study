package Week12_오마카세;

import java.io.*;

public class BOJ_1725_히스토그램 {
    static int N, height[], tree[];
    static long max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        height = new int[N + 1];
        tree = new int[4 * N];

        for (int i = 1; i <= N; i++)
            height[i] = Integer.parseInt(br.readLine());

        height[0] = Integer.MAX_VALUE;
        init(1, 1, N);
        solve(1, N);

        System.out.println(max);
    }

    private static void solve(int l, int r) {
        if (l > r) return;
        else if (l == r) {
            max = Math.max(max, height[l]);
            return;
        }

        int idx = query(1, 1, N, l, r); // minIdx
        max = Math.max(max, (r - l + 1) * height[idx]);

        solve(l, idx - 1);
        solve(idx + 1, r);
    }

    private static int query(int node, int l, int r, int ql, int qr) {
        if (r < ql || qr < l) return 0;
        else if (ql <= l && r <= qr) return tree[node];

        int mid = (l + r) / 2;
        int li = query(node * 2, l, mid, ql, qr);
        int ri = query(node * 2 + 1, mid + 1, r, ql, qr);

        return height[li] <= height[ri] ? li : ri;
    }

    private static int init(int node, int l, int r) {
        if (l == r) {
            return tree[node] = l;
        }

        int mid = (l + r) / 2;
        int li = init(node * 2, l, mid);
        int ri = init(node * 2 + 1, mid + 1, r);

        return tree[node] = height[li] <= height[ri] ? li : ri;
    }
}