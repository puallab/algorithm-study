import java.util.*;
import java.io.*;

public class 공유기_설치{
	static int n, c;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[n];
		for(int i =0; i<n; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		int left =0;
		int right = (int)1e9;
		int ans = 0;
		while(left <= right){
			int mid = (left+right)/2;
			int cnt = 1;
			int idx = arr[0];
			for(int i =1; i<n; i++){
				if(arr[i] >= idx+mid){
					cnt++;
					idx = arr[i];
				}
			}

			if(cnt >= c){
				ans = mid;
				left= mid +1;
			}else{
				right = mid-1;
			}
		}
		System.out.println(ans);
	}
}