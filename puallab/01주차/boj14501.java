import java.util.*;
import java.io.*;

public class boj14501{
    static int n, ans;
    static int[][] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); //Ti
            arr[i][1] = Integer.parseInt(st.nextToken()); //Pi
        }
        
        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int day, int sum){
        if(day == n){
            ans = Math.max(ans, sum);
            return;
        }

        //선택함
        if(day+arr[day][0] <= n) dfs(day+arr[day][0], sum+arr[day][1]);
        //선택안함
        dfs(day+1, sum);
    }
}