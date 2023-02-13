package Week6_분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17398_통신망_분할 {
    static int N, M, Q, qrys[][], roots[], count[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        init();
        qrys = new int[M + 1][2];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            qrys[i] = new int[]{a, b};
        }

        int[] orders = new int[Q];
        boolean[] flag = new boolean[M + 1];
        for (int i = 0; i < Q; i++) {
            orders[i] = Integer.parseInt(br.readLine());
            flag[orders[i]] = true;
        }

        for (int i = 1; i <= M; i++) {
            if (flag[i]) continue;

            int pa = find(qrys[i][0]);
            int pb = find(qrys[i][1]);

            union(pa, pb);
        }

        long ans = 0;
        for (int i = Q - 1; i >= 0; i--) {
            int idx = orders[i];

            int pa = find(qrys[idx][0]);
            int pb = find(qrys[idx][1]);

            ans += union(pa, pb);
        }

        System.out.println(ans);
    }

    private static void init() {
        roots = new int[N + 1];
        count = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            roots[i] = i;
            count[i] = 1;
        }
    }

    private static int find(int x) {
        if (roots[x] == x) return x;
        return roots[x] = find(roots[x]);
    }

    private static long union(int pa, int pb) {
        if (pa == pb) return 0;

        long cost = (long)count[pa] * count[pb]; // long.....하아..
        if (pa < pb) {
            roots[pa] = pb;
            count[pb] += count[pa];
        } else {
            roots[pb] = pa;
            count[pa] += count[pb];
        }

        return cost;
    }
}