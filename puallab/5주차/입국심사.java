import java.util.*;
import java.io.*;

public class 입국심사{
	static int n, m;
	static int[] t;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = new int[n];
        //long ...
        long max=0;
		for(int i=0; i<n; i++){
			t[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, t[i]);
		}

		long left = 1;
		long right = max*m;
		long ans = 0;
		while(left <= right){
			long mid = (left+right)/2;
			long pass = getPass(mid);
			if(pass >= m){
				ans = mid;
				right = mid-1;
			}else if(pass < m){
				left = mid+1;
			}
		}
		System.out.println(ans);
	}

	static long getPass(long time){
		long cnt = 0;
		for(int i =0; i<n; i++){
			cnt += time/t[i];
		}
		return cnt;
	}
}