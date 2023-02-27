import java.util.*;
import java.io.*;

public class 중앙값_측정{
	static int n, k, N, startIdx;
	static long ans;
	static int[] tree;
	static Queue<Integer> qu = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		tree = new int[65535 * 4];
		for(N=1; 1<<N <= 65535; N++);
		startIdx = 1 << N;
		
		for(int i =1; i<=n; i++){
			int q = Integer.parseInt(br.readLine());
			update(q, 1);
			qu.add(q);
			if(i < k) continue;
			ans += getAns((k+1)/2);
			update(qu.poll(), -1);
		}
		System.out.println(ans);

	}

	static void update(int idx , int val){
		idx += startIdx;

		while(idx > 0){
			tree[idx] += val;
			idx /= 2;
		}
	}

	static int getAns( int val){
		int idx = 1;
		int ret = 0;
		while(idx < 1<<N ){

			if(tree[idx*2] < val){
				val -= tree[idx*2];
				idx = idx*2 +1; 
			}else {
				idx *= 2;
			}

			if(idx >= startIdx && tree[idx] >= val){ 
				ret = idx;
				break;
			}
 
		}	

		return ret - startIdx;
	}
}