package Week6_분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16562_친구비 {
    static int N, M, roots[], coin, costs[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        coin = Integer.parseInt(st.nextToken());
        roots = new int[N + 1];
        costs = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            roots[i] = i;
            costs[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int pa = find(a);
            int pb = find(b);

            if (pa != pb) {
                if (costs[pa] > costs[pb]) roots[pa] = pb;
                else roots[pb] = pa;
            }
        }

        System.out.println(solve());
    }

    private static int find(int x) {
        if (roots[x] == x) return x;
        return roots[x] = find(roots[x]);
    }

    private static Object solve() {
        int ans = 0;

        for (int i = 1; i <= N; i++) {
            if (roots[i] != i) continue;
            ans += costs[i];
        }

        return ans > coin ? "Oh no" : ans;
    }
}
