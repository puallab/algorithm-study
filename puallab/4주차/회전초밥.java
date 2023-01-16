import java.util.*;
import java.io.*;

public class 회전초밥{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		long[] vis = new long[30001];

		for(int i =0; i<n; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}

		//초기 값
		int ans =0, cnt = 0;
		for(int i =0; i<k; i++){
			int idx = i%n;
			if(vis[arr[idx]] == 0) cnt += 1;
			vis[arr[idx]] += 1;
		}
		if(vis[c] == 0) ans = cnt+1;
		else ans = cnt;

		for(int i =1; i<n; i++){
			//이전 삭제
			vis[arr[i-1]] -= 1;
			if(vis[arr[i-1]] == 0 ) cnt -= 1;
			
			//추가되는거 추가
			int idx = (i+k-1)%n;
			if(vis[arr[idx]] == 0) cnt += 1;
			vis[arr[idx]] += 1;

			if(vis[c] == 0 ){
				ans = Math.max(ans, cnt+1);
				continue;
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}
}