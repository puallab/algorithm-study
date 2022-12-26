import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// N과 M (9)
public class MRC_15663 {
    static int N, M;
    static int[] num;
    static int[] numCnt;
    static int[] visit;
    static StringBuilder sb;
    static int typeCnt;

    public static void main(String[] args) throws IOException {

        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N];
        numCnt = new int[10001];
        visit = new int[M];
//        typeCnt = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (numCnt[number] == 0) {
                num[i] = number;// 0,1,7,9
                //typeCnt++;
            }
            numCnt[number]++;
        }

        Arrays.sort(num);
        permutation(0);

        System.out.println(sb.toString());
    }

    static void permutation(int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++)
                sb.append(visit[i]).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (numCnt[num[i]] > 0) {
                visit[cnt] = num[i];
                numCnt[num[i]]--;
                permutation(cnt + 1);
                numCnt[num[i]]++;
            }
        }
    }
}
