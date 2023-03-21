import java.io.*;
import java.util.*;


public class 친구_네트워크{
	static int t, n, seq;
	static int[] parents, friends;
	static HashMap<String, Integer> map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		while(t-- > 0){
			n = Integer.parseInt(br.readLine());
			seq = 0;
			map = new HashMap<>();
			parents = new int[2*n];
			friends = new int[2*n];
			for(int i=0; i<2*n; i++){
				parents[i] =i;
				friends[i] = 1;
			}
			
			for(int i =0; i<n; i++){
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				if(!map.containsKey(a)){
					map.put(a, seq++);
				}
				if(!map.containsKey(b)){
					map.put(b, seq++);
				}

				int paIdx = find(map.get(a));
				int pbIdx = find(map.get(b));
				if(paIdx == pbIdx){
					sb.append(friends[paIdx]).append("\n");
				}else{
					union(pbIdx, paIdx);
					sb.append(friends[find(pbIdx)]).append("\n");
				}
			}
		}	
        System.out.println(sb.toString());
	}

	static int find(int a){
		if(parents[a] == a){
			return a;
		}

		return parents[a] = find(parents[a]);
	}

	static void union(int a, int b){
		int pa = find(a);
		int pb = find(b);

		friends[pa] += friends[pb];
		parents[pb] = pa;
	}
}