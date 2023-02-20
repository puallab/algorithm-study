import java.util.*;
import java.io.*;

public class 두_배열의_합_이분탐색{
	static int t, n, m;
	static int[] nSum, mSum;
	static List<Integer> list = new ArrayList<>();
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
				int val = nSum[i] - nSum[j];
				int find = t - val;
				list.add(find);
			}
		}
		Collections.sort(list);

		for(int i = 1; i<=m; i++){
			for(int j=0; j<i; j++){
				int val = mSum[i] - mSum[j];
				//이분 탐색 두번 진행
				int leftIdx = findLeft(val);
				int rightIdx = findRight(val);
				if(leftIdx > rightIdx ) continue;
				cnt += rightIdx-leftIdx-1;
			}
		}
		System.out.println(cnt);
	}

	static int findLeft(int val){

		int left =0, right = list.size() -1;
		int idx = left-1;
		while(left <= right){
			int mid = (right+left)/2;
			if(list.get(mid) < val){
				left= mid+1;
				idx = mid;
			}else{
				right = mid-1;
			}
		}
		return idx;
	}

	static int findRight(int val){

		int left =0, right = list.size() -1;
		int idx = right+1;
		while(left <= right){
			int mid = (right+left)/2;
			if(list.get(mid)  > val){
				right = mid -1;
				idx = mid;
			}else{
				left = mid +1;
			}
		}
		return idx;
	}

}