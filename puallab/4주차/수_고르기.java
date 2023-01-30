import java.util.*;
import java.io.*;

public class 수_고르기{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for(int i =0; i<n; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int left =0, right = 1;
		long ans = Integer.MAX_VALUE;
		while(right < arr.length){
			long k = arr[right]-arr[left];
			if(k==m){
				ans = m;
				break;
			}else if (k < m){
				right++;
			}else{
				ans = Math.min(ans, k);
				left++;
			}
		}
		System.out.println(ans);
	}
}