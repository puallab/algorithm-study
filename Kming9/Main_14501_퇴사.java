package _1주차_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501_퇴사 {
	
	static int N, ans;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N+1][2];
		ans = 0;
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // T
			arr[i][1] = Integer.parseInt(st.nextToken()); // P
		}
		
		dfs(1, 0);
		
		System.out.println(ans);
	}

	private static void dfs(int idx, int cost) {
		ans = Math.max(ans, cost);
		
		for(int i=idx; i<=N; i++)
		{
			if(i + arr[i][0] <= N+1) 
				dfs(i+ arr[i][0], cost + arr[i][1]);
		}
	}
}
