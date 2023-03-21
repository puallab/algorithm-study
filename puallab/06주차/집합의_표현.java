import java.io.*;
import java.util.*;

public class 집합의_표현{
	static int n, m;
	static int[] parents;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		parents = new int[n+1];
		for(int i =0; i<=n; i++){
			parents[i] = i;
		}

		for(int i =0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(op == 0){
				union(a, b);
			}else{
				if(find(a) == find(b)){
					sb.append("YES").append("\n");
				}else{
					sb.append("NO").append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}

	static void union(int a, int b){
		int pa = find(a);
		int pb = find(b);

		parents[pa] = pb;
	}

	static int find(int a){

		if(parents[a] == a){
			return a;
		}

		return parents[a] = find(parents[a]);
	}

}