import java.util.*;
import java.io.*;

public class 구간_곱_구하기{
	static int n, m, k, t, startIdx;
	static int MOD = (int)1e9 + 7;
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
		Arrays.fill(tree, 1);

		for(int i=1; i<=n; i++){
			arr[i] = Long.parseLong(br.readLine())%MOD;
			update(i, 1, arr[i]);
		}

		

		for(int i =0; i< m+k; i++){
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if(a == 1){
				
				long prev = arr[b];
				arr[b] = c;
				update(b, prev, c);
			}else{
				long val = dot(b, (int)c);
				sb.append(val + "\n");
			}

		}

		System.out.println(sb.toString());

	}

	static void update(int idx, long pre, long val){
		int treeIdx = idx + startIdx -1;

		while(treeIdx >= 1){
            if(treeIdx >= startIdx) tree[treeIdx] = val%MOD;
			else tree[treeIdx] = tree[treeIdx*2]%MOD * tree[treeIdx*2+1]%MOD;
			treeIdx /= 2;
			
		}
	}

	static long dot(int left, int right){
		int leftIdx = left + startIdx -1;
		int rightIdx = right + startIdx -1;
		long val = 1;
		while(leftIdx < rightIdx){

			if(leftIdx%2 == 1){
				val = val%MOD * tree[leftIdx]%MOD;
				leftIdx += 1;
			}
			leftIdx /= 2;

			if(rightIdx%2 == 0){
				val = val%MOD * tree[rightIdx]%MOD;
				rightIdx -= 1;
			}
			rightIdx /= 2;

		}

		if(leftIdx == rightIdx){
			val = val%MOD * tree[leftIdx]%MOD;
		}

		return val;
	}

}