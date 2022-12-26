import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// N과 M (6)
public class MRC_15655 {
    static int N, M;
    static int[] num;
    static int[] visit;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N];
        visit = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);

        combination(0, 0,0);

        System.out.println(sb.toString());
    }

    static void combination(int cnt, int flag, int start){
        if (cnt == M) {
            for(int i= 0; i< M ;i++)
                sb.append(visit[i]).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            if ((flag & 1 << i) == 0) {
                visit[cnt] = num[i];
                combination(cnt + 1, (flag | 1 << i) ,i+1); // flag + Math.pow(2,i);
            }
        }
    }
}
