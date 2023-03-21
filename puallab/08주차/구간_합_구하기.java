import java.util.*;
import java.io.*;

public class 구간_합_구하기{
	static int n, m, k, t, startIdx;
	static long[] arr, tree;
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for(t = 1; 1<<t <= n; t++){
			startIdx = t;
		}
		startIdx = 1 << t;

		arr = new long[n+1];
		tree = new long[n*4];
		for(int i=1; i<=n; i++){
			arr[i] = Long.parseLong(br.readLine());
			update(i, arr[i]);
		}

		

		for(int i =0; i< m+k; i++){
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if(a == 1){
				
				long diff = c - arr[b];
				arr[b] = c;
				update(b, diff);
			}else{
				long val = sum(b, (int)c);
				sb.append(val + "\n");
			}

		}

		System.out.println(sb.toString());

	}

	static void update(int idx, long diff){
		int treeIdx = idx + startIdx -1;

		while(treeIdx > 1){
			tree[treeIdx] += diff;
			treeIdx /= 2;
		}
	}

	static long sum(int left, int right){
		int leftIdx = left + startIdx -1;
		int rightIdx = right + startIdx -1;
		long val = 0;
		while(leftIdx < rightIdx){

			if(leftIdx%2 == 1){
				val += tree[leftIdx];
				leftIdx += 1;
			}
			leftIdx /= 2;

			if(rightIdx%2 == 0){
				val += tree[rightIdx];
				rightIdx -= 1;
			}
			rightIdx /= 2;

		}

		if(leftIdx == rightIdx){
			val += tree[leftIdx];
		}

		return val;
	}

}