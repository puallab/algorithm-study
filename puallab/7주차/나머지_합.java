import java.util.*;
import java.io.*;

public class 나머지_합{
	static int n, m;
	static long[] arr;
	static long[] vis;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new long[n+1];
		vis = new long[m];
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<n; i++){
			arr[i+1] = (arr[i] + Integer.parseInt(st.nextToken()))%m;
			vis[(int)arr[i+1]] += 1;
		}

		long ans = vis[0];
		for(int i =0; i<m; i++){
			ans += (vis[i]*(vis[i]-1))/2;
		}
		System.out.println(ans);
		
	}
}