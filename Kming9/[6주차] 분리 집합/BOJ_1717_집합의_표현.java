package Week6_분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717_집합의_표현 {
    static int N, M, root[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int exp = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int pa = find(a);
            int pb = find(b);

            if (exp == 0) union(pa, pb);
            else if (pa == pb) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.print(sb);
    }

    private static void init() {
        root = new int[N + 1];
        for (int i = 1; i <= N; i++)
            root[i] = i;
    }

    private static int find(int x) {
        if(root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    private static void union(int a, int b) {
        if(a == b) return;
        root[a] = b;
    }
}
