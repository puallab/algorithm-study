import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MRC_14501 {
    static int N; // N+1일 까지 근무!
    static int[] time;
    static int[] pay;
    static int maxPay;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        time = new int[N + 1];
        pay = new int[N + 1];
        maxPay = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }

        solve(1, 0);

        System.out.println(maxPay);
    }

    public static void solve(int day, int sumPay) {
        maxPay = Math.max(maxPay, sumPay);
        for (int workDay = day; workDay <= N; workDay++) {
            if (workDay + time[workDay] <= N + 1) { // i일 업무 선택
                solve(workDay + time[workDay] , sumPay + pay[workDay]);
            }
        }
    }
}
