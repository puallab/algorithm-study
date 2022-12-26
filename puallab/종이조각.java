import java.util.*;
import java.io.*;

public class Main{
    static int n, m, ans;
    static int[][] arr;
    static boolean[][] vis;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        vis = new boolean[n][m];
        
        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j =0; j<m; j++){
                arr[i][j] = s.charAt(j)-'0';
            }
        }

        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int idx){
        if(idx == n*m){
            ans = Math.max(ans, getVal());
            return;
        }
        //가로
        vis[idx/m][idx%m] = true;
        dfs(idx+1);

        //세로
        vis[idx/m][idx%m] = false;
        dfs(idx+1);
    }

    static int getVal(){
        int val =0, temp =0;
        //가로 먼저 계산
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(vis[i][j]){
                    temp *= 10;
                    temp += arr[i][j];
                }else{
                    val += temp;
                    temp =0;
                }
            }
            val += temp;
            temp =0;
        }

        //세로 계산
        for(int j=0; j<m; j++){
            for(int i =0; i<n; i++){
                if(!vis[i][j]){
                    temp *=10;
                    temp += arr[i][j];
                }else{
                    val += temp;
                    temp = 0;
                }
            }
            val += temp;
            temp  =0;
        }
        return val;
    }
}