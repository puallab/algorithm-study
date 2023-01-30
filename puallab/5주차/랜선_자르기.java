import java.util.*;
import java.io.*;

public class 랜선_자르기{
	static int k, n;
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[k];

		for(int i =0; i<k; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}

		long left = 1;
		long right = Integer.MAX_VALUE;
		long ans =0;
		while(left <= right){
			long mid = (left+right)/2;
			long cnt = getCnt(mid);
			if(cnt >= n){
				ans = mid;
				left = mid+1;
			}else{
				right = mid -1;
			}
		}
		System.out.println(ans);
	}

	static long getCnt(long cm){
		long val =0;

		for(int i =0; i<arr.length; i++){
			val += arr[i]/cm;
		}

		return val;
	}
}