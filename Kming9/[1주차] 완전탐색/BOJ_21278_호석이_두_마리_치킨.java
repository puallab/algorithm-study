package Week1_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21278_호석이_두_마리_치킨 {

    static int N, M, dist[][], ans[];
    static ArrayList<Integer>[] adj;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N + 1][N + 1];
        adj = new ArrayList[N + 1];
        ans = new int[] {INF, INF, INF};

        for (int i = 1; i <= N; i++)
            for(int j = 1; j <=N ; j++)
                dist[i][j] = INF;

        for (int i = 0; i <= N; i++)
            adj[i] = new ArrayList<>();

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            dist[i][i] = 0;
            bfs(i);
        }

        combination(1, 0, new int[2]);

        for(int i=0; i<3; i++)
            sb.append(ans[i]).append(" ");

        sb.append("\n");
        System.out.println(sb.toString());
    }

    private static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);

        int cur, tmp;
        while (!q.isEmpty()) {
            cur = q.poll();

            for (int i = 0; i < adj[cur].size(); i++) {
                tmp = adj[cur].get(i);

                if (dist[v][tmp] > dist[v][cur] + 1) {
                    dist[v][tmp] = dist[v][cur] + 1;
                    q.offer(tmp);
                }
            }
        }
    }

    private static void combination(int idx, int cnt, int[] picked) {
        if(cnt == 2)
        {
            int sum = cal(picked[0], picked[1]);

            if(sum < ans[2]) {
                ans[0] = picked[0];
                ans[1] = picked[1];
                ans[2] = sum;
            }

            return;
        }

        for(int i=idx; i<=N; i++) {
            picked[cnt] = i;
            combination(i+1, cnt+1, picked);
        }
    }

    private static int cal(int a, int b) {
        int sum = 0;

        for(int i=1; i<=N; i++) {
            sum += Math.min(dist[a][i], dist[b][i]);
        }

        return sum * 2;
    }
}