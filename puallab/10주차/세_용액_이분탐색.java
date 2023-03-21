import java.util.*;
import java.io.*;

public class 세_용액_이분탐색 {
	static int n;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		long minVal = Long.MAX_VALUE;
		int[] ans = new int[3];
		for(int i =0; i<n; i++){
			for(int j =i+1; j<n; j++){
				long val = (long)arr[i]+arr[j];
				int findIdx = find(i, j, val);
				if(findIdx == -1) continue;
				if(minVal > Math.abs(val+arr[findIdx])){
					ans[0] = arr[i];
					ans[1] = arr[j];
					ans[2] = arr[findIdx];
					minVal = Math.abs(val+arr[findIdx]);
				}
			}
		}
		Arrays.sort(ans);
		System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
	}

	static int find(int idx1, int idx2, long val){
		int left  =0;
		int right = n-1;
		long target = Long.MAX_VALUE;
		int idx = -1;
		
		while(left <= right){
			int mid = (left+right)/2;
			if(val+arr[mid] < 0){
				left = mid +1;
			}else if(val+arr[mid] >= 0){
				right = mid -1;
			}
			if(Math.abs(val+arr[mid]) < Math.abs(target)){
				if(mid == idx1 || mid == idx2) continue;
				target = Math.abs(val+arr[mid]);
				idx = mid;
			}
		}

		return idx;
	}
}