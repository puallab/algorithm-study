package Week8_세그먼트_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2243_사탕상자 {
    static int N = 1_000_000, tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        tree = new int[4 * N];
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) {
                sb.append(query(1, N, 1, b)).append("\n");
                continue;
            }

            int c = Integer.parseInt(st.nextToken());
            update(1, N, 1, b, c);
        }
        System.out.print(sb);
    }

    public static int query(int l, int r, int node, int x) {
        tree[node]--;
        if (l == r) return l;

        int mid = (l + r) / 2;
        int left = tree[node * 2];
        return left < x ? query(mid + 1, r, node * 2 + 1, x - left) : query(l, mid, node * 2, x);
    }

    public static void update(int l, int r, int node, int idx, int val) {
        if (idx < l || idx > r) return;

        tree[node] += val;
        if (l == r) return;

        int mid = (l + r) / 2;
        update(l, mid, node * 2, idx, val);
        update(mid + 1, r, node * 2 + 1, idx, val);
    }
}