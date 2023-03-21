import java.util.*;
import java.io.*;

public class 사탕상자{
    static int n, N = (int)1e6, k, startIdx;
	static int[] arr, tree;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		for(k = 1; 1<<k <= N; k++);
		startIdx = 1<<k;

		tree = new int[4*N+1];


		while(n-- >0){
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a= Integer.parseInt(st.nextToken());
			
			if(cmd == 2){
				int b = Integer.parseInt(st.nextToken());
				arr[a] += b;
				update(a, b);
			}else{
				sb.append(find(a)).append("\n");
			}
		}

		System.out.println(sb.toString());
    }

	static void update(int idx, int diff){
		idx += startIdx-1;

		while(idx >= 1){
			tree[idx] += diff;
			idx /= 2;
		}
	}

	static int find(int cnt){
		int node = 1;
		while(node < startIdx){

			if(tree[node*2] >= cnt){
				node = node*2;
			}else{
				cnt = cnt - tree[node*2];
				node = node*2 +1;
			}
		}
		update(node-startIdx+1, -1);
		return node-startIdx+1;
	}
}
