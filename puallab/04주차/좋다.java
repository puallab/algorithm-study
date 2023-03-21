import java.util.*;
import java.io.*;

public class 좋다{
	static int n;
	static long[] arr;
	static HashSet<Long> hashSet = new HashSet<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new long[n];

		st = new StringTokenizer(br.readLine());
		for(int i =0 ; i<n; i++){
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);
		int cnt = 0;
		for(int i=0; i<arr.length; i++){
			if(hashSet.contains(arr[i])) cnt += 1;
			else if(isPossible(i)){
				cnt += 1;
				hashSet.add(arr[i]);
			}
		}
		System.out.println(cnt);
	}

	static boolean isPossible(int idx){
		int left = 0, right = n-1;
		if(left == idx){
			left += 1;
		}
		else if(right == idx){
			right -= 1;
		}
		while(left < n && right < n){
			
			long gap = arr[right] + arr[left];
			if(left == right) return false;
			if(gap == arr[idx]) return true;
			else if(gap < arr[idx]) left++;
			else right--;
			if(left == idx) left++;
			else if(right == idx) right--;
			
		}
		
		return false;
	}
}