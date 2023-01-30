import java.util.*;
import java.io.*;

public class 구간_나누기{
	static int n, m;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = 10000;
		int val = Integer.MAX_VALUE;
		while(left <= right){
			int mid = (left+right)/2;
			int cnt = getCnt(mid);
			if(cnt <= m){
				right = mid -1;
				val = mid;
			}else{
				left = mid +1;
			}
		}
		System.out.println(val);
	}

	static int getCnt(int val){
		int cnt = 1;
		int min = 10000;
		int max =0;
		for(int i =0; i<n; i++){
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);

			if(max-min > val){
				cnt+=1;
				max = arr[i];
				min = arr[i];
			}

		}
		return cnt;
	}
}