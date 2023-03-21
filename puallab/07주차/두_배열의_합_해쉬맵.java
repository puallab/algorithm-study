import java.util.*;
import java.io.*;

public class 두_배열의_합_해쉬맵{
	static int t, n, m;
	static long[] nSum, mSum;
	static Map<Long, Long> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		t = Integer.parseInt(br.readLine());
		
		n = Integer.parseInt(br.readLine());
		nSum = new long[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<n; i++){
			nSum[i+1] = nSum[i] + Integer.parseInt(st.nextToken());
		}

		m = Integer.parseInt(br.readLine());
		mSum = new long[m+1];
		st= new StringTokenizer(br.readLine());
		for(int i =0; i<m; i++){
			mSum[i+1] = mSum[i] + Integer.parseInt(st.nextToken());
		}
		long cnt = 0;
		for(int i = 1; i<=n; i++){
			for(int j=0; j<i; j++){
				long val = nSum[i] - nSum[j];
				long find = t - val;
				map.put(find, map.getOrDefault(find, 0L) +1);
			}
		}

		for(int i = 1; i<=m; i++){
			for(int j=0; j<i; j++){
				long val = mSum[i] - mSum[j];
				cnt += map.getOrDefault( val, 0L);
			}
		}
		System.out.println(cnt);
	}
}