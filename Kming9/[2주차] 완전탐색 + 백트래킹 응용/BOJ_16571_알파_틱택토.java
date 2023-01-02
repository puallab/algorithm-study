package Week2_백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16571_알파_틱택토 {
    static int player, map[][], count[], N = 3;
    static char ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[N][N];
        count = new int[N];
        ans = 'D';

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                count[map[y][x]]++;
            }
        }

        player = count[1] <= count[2] ? 1 : 2;
        dfs(player, count[0]);

        System.out.println(ans);
    }

    public static void dfs(int num, int cnt) {
        int res = play();
        if(res == player) {
            ans = 'W';
            return;
        } else if(res > 0){
            ans = 'L';
            return;
        }
        
        if(ans != 'D' || cnt == 0)  return;
        
        for(int y=0; y<N; y++) {
            for(int x=0; x<N; x++) {
                if(map[y][x] != 0) continue;

                int nextPlayer = num == 1 ? 2 : 1;
                map[y][x] = num;
                dfs(nextPlayer, cnt-1);
                map[y][x] = 0;
            }
        }
    }
    
    public static int play() {
        for(int i=0; i<N; i++)
           if(map[i][0] != 0 && map[i][0] == map[i][1] && map[i][1] == map[i][2]) return map[i][0];

        for(int i=0; i<N; i++)
            if(map[0][i] != 0 && map[0][i] == map[1][i] && map[1][i] == map[2][i]) return map[0][i];

        if(map[0][0] != 0 && map[0][0] == map[1][1] && map[1][1] == map[2][2]) return map[1][1];
        if(map[0][0] != 0 && map[0][2] == map[1][1] && map[1][1] == map[2][0]) return map[1][1];

        return -1;
    }
}
