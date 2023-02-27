package Week6_분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10775_공항 {
    static int G, P, roots[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        roots = new int[G + 1];
        for (int i = 1; i <= G; i++) roots[i] = i;

        int ans = 0;
        for (int i = 0; i < P; i++, ans++) {
            int x = Integer.parseInt(br.readLine());
            int px = find(x);

            if (px == 0) break;
            roots[px] = px - 1;
        }

        System.out.println(ans);
    }

    private static int find(int x) {
        if (roots[x] == x) return x;
        return roots[x] = find(roots[x]);
    }
}
