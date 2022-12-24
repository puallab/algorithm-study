import java.util.*;
import java.io.*;

public class Main{
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr); // 오름차순으로 정렬
        
        dfs(0, 0, "");
        System.out.println(sb.toString());
    }

    static void dfs(int idx, int picked, String s){
        if(picked == m){
            sb.append(s.trim()+"\n");
            return;
        }

        for(int i =idx; i<n; i++){
            dfs(i+1, picked+1, s+arr[i]+" ");
        }
        
    }
}