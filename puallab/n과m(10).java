import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static int n, m;
    static int[] arr;
    static boolean[] vis;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        vis = new boolean[n];
        for(int i= 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        dfs(0, 0, "");
        System.out.println(sb.toString());

    } 

    static void dfs(int idx, int picked, String s){
        if(picked == m){
            sb.append(s.trim()+"\n");
            return;
        }
        int prev = -1;
        for(int i =idx; i<n; i++){
            if(vis[i] || arr[i] == prev) continue;
            vis[i] = true;
            prev= arr[i];
            dfs(i+1, picked+1, s + " " + arr[i]);
            vis[i] = false;
        }
    }
}