import java.util.*;
import java.io.*;

public class 사냥꾼{

	static class Pair{
		int y, x;
		public Pair(int yy, int xx){
			y = yy;
			x = xx;
		}

	}

	static int m, n, l, ans;
	static int[] shooters;
	static Pair[] targets;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		shooters = new int[m];
		for(int i =0; i<m; i++){
			shooters[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(shooters);

		targets =  new Pair[n];
		for(int i =0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			ans += isPossible(x, l-y);
		}
		System.out.println(ans);
	}
	
	static int isPossible(int x, int y){
		if(y < 0) return 0;
		int leftPosiible = x - y > 0 ? x-y : 0 ;
		int rightPossible = x + y < (int)1e9 ? x+y : (int)1e9;
		int leftIdx = -1;
		int rightIdx = m;

		int left = 0;
		int right = m-1;
	
		while(left <= right){
			int mid = (left+right)/2;
			if(leftPosiible <= shooters[mid]  ) {
				leftIdx = mid;
				right = mid -1;
			}
			else left = mid +1;
		}

		if( leftIdx != -1 && shooters[leftIdx] <= rightPossible) return 1;

		left = 0;
		right = m-1;
		while(left <= right){
			int mid = (left+right)/2;
			if(shooters[mid] <= rightPossible) {
				rightIdx = mid;
				left = mid +1;
			}
			else right = mid -1;
		}

		if(rightIdx != m && shooters[rightIdx] >= leftPosiible) return 1;
		return 0;
	}
}