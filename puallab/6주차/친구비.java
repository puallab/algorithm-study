import java.io.*;
import java.util.StringTokenizer;

public class 친구비{
	static int n, m, k;
	static int[] parents;
	static int[] fee;
	static boolean[] vis;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		parents = new int[n+1];
		for(int i =0; i<n+1; i++){
			parents[i] = i;
		}
		fee = new int[n+1];
		vis = new boolean[n+1];

		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++){
			fee[i] = Integer.parseInt(st.nextToken());
		}

		for(int i =0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int pa = find(a);
			int pb = find(b);
			if(pa != pb){
				if(fee[pa] <= fee[pb]){
					union(pa, pb);
				}else{
					union(pb, pa);
				}
			}
		}

		int pay = 0;
		for(int i =1; i<n+1; i++){
			int idx = find(i);
			if(!vis[idx]){
				vis[idx] = true;
				pay += fee[idx];
			}

			if(k < pay){
				System.out.println("Oh no");
				return;
			}
		}

		System.out.println(pay);
		
	}

	static int find(int a){
		if(a == parents[a]){
			return a;
		}
		return parents[a] = find((parents[a]));
	}

	static void union(int a, int b){
		int pa = find(a);
		int pb = find(b);

		parents[pb] = pa;
	}
}