package Week6_분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_4195_친구_네트워크 {
    static HashMap<String, String> roots;
    static HashMap<String, Integer> count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            roots = new HashMap<>();
            count = new HashMap<>();
            int F = Integer.parseInt(br.readLine());

            for (int j = 0; j < F; j++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                String pa = find(a);
                String pb = find(b);

                if (pa == pb) sb.append(count.get(pa));
                else {
                    roots.replace(pa, pb);
                    count.replace(pb, count.get(pb) + count.get(pa));
                    sb.append(count.get(pb));
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static String find(String x) {
        if (!roots.containsKey(x)) {
            roots.put(x, x);
            count.put(x, 1);
            return x;
        } else if (roots.get(x) == x) return x;

        String px = find(roots.get(x));
        roots.replace(x, px);

        return px;
    }
}
