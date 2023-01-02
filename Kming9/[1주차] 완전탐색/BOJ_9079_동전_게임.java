package Week1_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9079_동전_게임 {
    static int front, back = 0;
    static boolean visit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        init();

        char c;
        for (int i = 0; i < T; i++) {
            int map = 0;
            visit = new boolean[1000];

            for (int y = 0; y < 3; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < 3; x++) {
                    c = st.nextToken().charAt(0);
                    if (c == 'T') {
                        map |= (1 << (8 - (y * 3 + x)));
                    }
                }
            }

            sb.append(bfs(map, 0)).append("\n");
        }
        System.out.println(sb);
    }

    static int flip[];
    public static void init() {
        front = Integer.parseInt("111111111", 2);
        int r = Integer.parseInt("111000000", 2), c = Integer.parseInt("100100100", 2);

        flip = new int[8];
        for(int i=0; i<3; i++) {
            flip[i] = r >> (i*3);
            flip[i+3] = c >> i;
        }

        flip[6] = Integer.parseInt("100010001", 2);
        flip[7] = Integer.parseInt("001010100", 2);
    }

    public static int bfs(int status, int cnt) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(status);
        visit[status] = true;

        int cur;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int n=0; n<size; n++) {
                cur = q.poll();
                if(check(cur)) return cnt;

                for(int i=0; i<8; i++) {
                    int next = cur ^ flip[i];

                    if(visit[next]) continue;
                    visit[next] = true;
                    q.offer(next);
                }
            }
            cnt++;
        }
        return -1;
    }

    public static boolean check(int status) {
        return status == front || status == back;
    }
}

