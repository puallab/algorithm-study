import java.io.*;
import java.util.*;

public class 가장_긴_짝수_연속한_부분_수열_large{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

		int left =0, right =0;
		int cnt = arr[left]%2 == 1? 1 : 0;
		
		int ans = right-left+1-cnt;
		while(true){
			if(right == n-1 || left == n-1) break;
			if(cnt == m){
				right++;
				if(arr[right]%2 ==1) cnt++;
				else ans = Math.max(ans, right-left+1-cnt);
			}else if(cnt < m){ 
				right++;
				if(arr[right]%2 == 1) cnt++;
				ans = Math.max(ans, right-left+1-cnt);
			}else{
				if(arr[left] %2 == 1) cnt--;
				left++;
				ans = Math.max(ans, right-left+1-cnt);
			}
			
		}
        System.out.println(ans);   
    }
}