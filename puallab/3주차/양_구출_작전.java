import java.util.*;
import java.io.*;

public class 양_구출_작전{
    static int n;
    static int[] island;
    static ArrayList<Integer>[] list; 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n+1];
        island = new int[n+1];
        for(int i =0; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        for(int i =2; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            char ch = st.nextToken().charAt(0);
            int cnt = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            list[p].add(i);
            if(ch == 'W') island[i] = -cnt;
            else island[i] = cnt;
        }
        
        System.out.println(dfs(1));
        
    }

    static long dfs(int idx){

        long alive = island[idx];
        for(int next : list[idx]){
            alive += dfs(next);
        }

        if(alive <=0) return 0;
        else return alive;
    }
}