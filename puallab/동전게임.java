import java.io.*;
import java.util.StringTokenizer;

public class Main{
    static int n, ans, cntH;
    static int[][] arr;
    static boolean[] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            arr = new int[3][3];
            vis = new boolean[8];
            cntH = 0;
            ans = Integer.MAX_VALUE;
            for(int i =0; i<3; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<3;j++){
                    char ch = st.nextToken().charAt(0);
                    if(ch == 'H'){
                        arr[i][j] = 1;
                    }else{
                        arr[i][j] = -1;
                    }
                }
            }
            dfs(0);
            if(ans == Integer.MAX_VALUE) ans = -1;
            System.out.println(ans);
        }

    } 

    static void dfs(int picked){

        if(picked >= ans) return;

        if(allIsSame()){
            ans = Math.min(ans, picked);
            return;
        }

        if(picked == 7){
            return;
        }

        for(int i =0; i<=7; i++){
            if(vis[i]) continue;
            vis[i] = true;
            cover(i);
            dfs(picked +1);
            cover(i);
            vis[i] = false;
        }
    }

    static boolean allIsSame(){
        int first = arr[0][0];
        for(int i =1; i<9;i++){
            
            if(first != arr[i/3][i%3]){
                return false;
            }
        }
        return true;
    }

    static void cover(int idx){
        
        if(idx < 3){
            arr[idx][0] *= -1;
            arr[idx][1] *= -1;
            arr[idx][2] *= -1;
        }else if (idx < 6){
            arr[0][idx%3] *= -1;
            arr[1][idx%3] *= -1;
            arr[2][idx%3] *= -1;
        }else if (idx == 6){
            arr[0][0] *= -1;
            arr[1][1] *= -1;
            arr[2][2] *= -1;
        } else if(idx == 7){
            arr[0][2] *= -1;
            arr[1][1] *= -1;
            arr[2][0] *= -1;
        }
    }

}