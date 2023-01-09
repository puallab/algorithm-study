package Week2_백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16637_괄호_추가하기 {
    static int N, ans = Integer.MIN_VALUE;
    static char inputs[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inputs = br.readLine().toCharArray();

        dfs(0, '+', inputs[0]-'0', 1);
        System.out.println(ans);
    }

    public static void dfs(int left, char exp, int right, int i) {
        int cur = calculate(left, exp, right);

        if (i >= inputs.length-1) {
            if (cur > ans)
                ans = cur;
            return;
        }

        dfs(cur, inputs[i], inputs[i+1]-'0', i+2);
        if(i+3 < inputs.length)
            dfs(cur, inputs[i], calculate(inputs[i+1]-'0', inputs[i+2], inputs[i+3]-'0'),i+4);
    }

    public static int calculate(int a, char exp, int b) {
        if (exp == '+') return a + b;
        else if (exp == '-') return a - b;
        else return a * b;
    }
}

