import java.util.*;
import java.io.*;

public class 두_배열의_합_투포인터{
	static int t, n, m;
	static int[] nSum, mSum;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		t = Integer.parseInt(br.readLine());
		
		n = Integer.parseInt(br.readLine());
		nSum = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<n; i++){
			nSum[i+1] = nSum[i] + Integer.parseInt(st.nextToken());
		}

		m = Integer.parseInt(br.readLine());
		mSum = new int[m+1];
		st= new StringTokenizer(br.readLine());
		for(int i =0; i<m; i++){
			mSum[i+1] = mSum[i] + Integer.parseInt(st.nextToken());
		}
		long cnt = 0;
		for(int i = 1; i<=n; i++){
			for(int j=0; j<i; j++){
				long val = nSum[i] - nSum[j];
				long find = t - val;
				int left=0, right =1;
				while(right <= m && left < right ){
					long ret = mSum[right] - mSum[left];
					if(find < ret){
						left++;
					}else if(find > ret){
						right++;
					}else{
						cnt++;
						right++;
					}
					if(right == left) right++;
				}
			}
		}
		System.out.println(cnt);
	}
}