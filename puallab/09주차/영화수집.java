import java.util.*;
import java.io.*;

public class 영화수집{
	static int t, n, m, startIdx;
	static int[] arr, tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t= Integer.parseInt(br.readLine());
		while(t-- >0){
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			arr = new int[n+1];
			tree = new int[(n+m)*4];
			startIdx = getIdx();
			
			for(int i =1; i<=n; i++){
				arr[i] = n-i+1;
				update(arr[i], 1);
			}


			st = new StringTokenizer(br.readLine());
			for(int i =1; i<=m; i++){
				int k = Integer.parseInt(st.nextToken());
				int pos = arr[k];
				sb.append(getSum(pos) + " ");
				update(pos, -1);
				arr[k] =  n + i;
				update(arr[k], 1);
			}
			sb.append("\n");

		}
		System.out.println(sb.toString());
	}

	static int getIdx(){

		int val;
		for(val = 1; 1<<val < n+m; val++);

		return 1<<val;
	}

	static void update(int idx, int val){

		idx = idx +startIdx -1;
		while(idx > 0){
			tree[idx] += val;
			idx /= 2;
		}
	}

	static int getSum(int idx){
		int left = idx+startIdx;
		int right = startIdx+n+m-1;
		int sum = 0;
		while(left < right){

			if(right%2 == 0){
				sum += tree[right];
				right--;
			}
			right /= 2;

			if(left%2 == 1){
				sum += tree[left];
				left++;
			}
			left /= 2;
		}

		if(left == right){
			sum += tree[left];
		}

		return sum;
	}
}