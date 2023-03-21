import java.util.*;
import java.io.*;

public class 통신망_분할{
	static int n, m, q;
    static long ans;
	static int[] parents, cnts, orders;
	static int[][] query;
	static boolean[] vis;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		parents = new int[n];
		cnts = new int[n];
		
		for(int i = 0; i<n; i++){
			parents[i] = i;
			cnts[i] = 1;
		}
		
		query = new int[m][2];
		vis = new boolean[m];

		for(int i =0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			query[i][0] = Integer.parseInt(st.nextToken())-1;
			query[i][1] = Integer.parseInt(st.nextToken())-1;
		}

		orders = new int[q];
		for(int i=0; i<q; i++){
			orders[i] = Integer.parseInt(br.readLine())-1;
			vis[orders[i]] = true;
		}

		//init link
		for(int i =0; i<m; i++){
			if(!vis[i]){
				union(query[i][0], query[i][1]);
			}
		}

		ans = 0;
		//reverse order link	
		for(int i =q-1; i>=0; i--){
			int idx = orders[i];
			union(query[idx][0], query[idx][1]);
		}
		
		System.out.println(ans);
	}

	static int find(int a){
		if(a == parents[a]){
			return a;
		}

		return parents[a] = find(parents[a]);
	}

	static void union(int a, int b){
		int pa = find(a);
		int pb = find(b);

		if(pa == pb) return;
		ans += (long)cnts[pa]*cnts[pb];
		cnts[pa] += cnts[pb];
		parents[pb] = pa;
	}

}