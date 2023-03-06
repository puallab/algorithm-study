package Week9_세그먼트_트리_응용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3653_영화_수집 {
    static int n, m, movies[], tree[], curIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(st.nextToken());
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            movies = new int[n + 1];
            for (int j = n; j > 0; j--)
                movies[j] = n - j; // 영화가 있는 INDEX(0부터 N-1)

            int N = n+ m;
            tree = new int[4 * N];
            init(1, 0, N - 1);

            curIdx = n - 1;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());

                if(movies[num] == curIdx) {
                    sb.append(0 + " ");
                    continue;
                }

                update(1, 0, N -1, movies[num]);
                int res = query(1, 0, N -1, movies[num], curIdx);

                movies[num] = ++curIdx;
                sb.append(res).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static int update(int node, int l, int r, int idx) {
        if (idx < l || idx > r) return tree[node];
        else if (l == r) return tree[node] = 0;

        int mid = (l + r) / 2;
        return tree[node] = update(node * 2, l, mid, idx) + update(node * 2 + 1, mid + 1, r, idx);
    }

    private static int query(int node, int l, int r, int ql, int qr) {
        if (r < ql || qr < l) return 0;
        else if (ql <= l && r <= qr) return tree[node];

        int mid = (l + r) / 2;
        return query(node * 2, l, mid, ql, qr) + query(node * 2 + 1, mid + 1, r, ql, qr);
    }

    private static int init(int node, int l, int r) {
        if (l == r) return tree[node] = 1;

        int mid = (l + r) / 2;
        return tree[node] = init(node * 2, l, mid) + init(node * 2 + 1, mid + 1, r);
    }
}