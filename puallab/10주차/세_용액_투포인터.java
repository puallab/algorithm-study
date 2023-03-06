import java.util.*;
import java.io.*;

public class 세_용액_투포인터 {
	static int n;
	static int[] arr, ans = new int[3];
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
		solve();
		Arrays.sort(ans);
		System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
	}

	static void solve(){
		long minVal = Long.MAX_VALUE;
		
		for(int i =0; i<n; i++){
			int fixed = arr[i];
			int left = i;
			int right = n-1;
			while(left< right){
				if(left == i){
					left++;
					continue;
				}
				if(right == i){
					right--;
					continue;
				}

				long val = (long)fixed + (long)arr[left] + arr[right];
				if(Math.abs(val) < minVal){
					minVal = Math.abs(val);
					ans[0] = arr[left];
					ans[1] = arr[right];
					ans[2] = fixed;
				}
				if(val > 0){
					right--;
				}else if(val <0){
					left++;
				}else{
					return;
				}
				
			}
		}
	}

}