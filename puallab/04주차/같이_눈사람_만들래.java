import java.util.*;
import java.io.*;

public class 같이_눈사람_만들래{
	static int n;
	static int ans = Integer.MAX_VALUE;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for(int i =0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		for(int i =0; i<n; i++){
			for(int j =i+1; j<n; j++){
				ans = Math.min(ans, getGap(i, j));
                if(ans == 0){
                    System.out.println(ans);
                    return;
                }
			}
		}
		System.out.println(ans);
	}

	static int getGap(int p1, int p2){
		int left = 0, right =n-1;
		int val = arr[p1]+arr[p2];
		int ret = Integer.MAX_VALUE;
	
		while(left < right){
            if(left == p1 || left == p2){
                left++;
                continue;
            }
            if(right == p1 || right == p2){
                right--;
                continue;
            }
            
			int sum = arr[left] + arr[right];
			ret = Math.min(ret, Math.abs(sum-val));
	
			if(sum < val){
				left += 1;
			}else if (sum > val){
				right -= 1;
			}else{
				return 0;
			}
		}
		return ret;
	}

}