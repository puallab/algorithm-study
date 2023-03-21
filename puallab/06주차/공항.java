import java.io.*;

public class 공항{
	static int n, p, ans;
	static int[] parents;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		parents = new int[n+1];
		for(int i =1; i<n+1; i++){
			parents[i] = i;
		}
		p = Integer.parseInt(br.readLine());
		
		while(p-- > 0){
			int a = Integer.parseInt(br.readLine());
			int pa = find(a);
			if(pa == 0){
				break;
			}
			
			ans++;
			parents[pa] = pa-1;
		}
		System.out.println(ans);
	}

	static int find(int a){
		if(a == parents[a]){
			return a;
		}

		return parents[a] = find(parents[a]);
	}
	
}