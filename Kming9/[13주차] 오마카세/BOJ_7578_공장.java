package Week12_오마카세;

import java.io.*;
import java.util.*;

public class BOJ_7578_공장 {
    static int N, tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer inputs = new StringTokenizer(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> hm = new HashMap<>();
        tree = new int[4 * N];

        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            hm.put(n, i);
        }

        long ans = 0;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(inputs.nextToken());
            ans += query(1, 1, N, hm.get(n), N);
            update(1, 1, N, hm.get(n));
        }

        System.out.println(ans);
    }

    private static int update(int node, int l, int r, int idx) {
        if (idx < l || idx > r) return tree[node];
        else if (l == r) return tree[node] = 1;

        int mid = (l + r) / 2;
        return tree[node] = update(node * 2, l, mid, idx) + update(node * 2 + 1, mid + 1, r, idx);
    }

    private static int query(int node, int l, int r, int ql, int qr) {
        if (r < ql || qr < l) return 0;
        else if (ql <= l && r <= qr) return tree[node];

        int mid = (l + r) / 2;
        return query(node * 2, l, mid, ql, qr) + query(node * 2 + 1, mid + 1, r, ql, qr);
    }
}