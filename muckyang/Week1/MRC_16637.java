import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MRC_16637 {
    static int N;
    static int[] num;
    static char[] calc;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = Integer.parseInt(input);
        input = br.readLine();

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                num[i % 2] = input.charAt(i);
            } else {
                calc[i % 2] = input.charAt(i);
            }
        }
    }
}
