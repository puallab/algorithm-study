package _1주차_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15654_N과_M_5 {
	
	static int N, M;
	static int[] arr;
	static boolean[] flag;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		flag = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		permutation(0, new int[M]);
		System.out.println(sb.toString());
	}

	
	private static void permutation(int cnt, int[] picked) {
		if(cnt == M) 
		{
			for(int i=0; i<M; i++)
				sb.append(picked[i]).append(" ");
			
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(flag[i]) continue;
		
			flag[i] = true;
			picked[cnt] = arr[i];
			permutation(cnt+1, picked);
			flag[i] = false;
		}
	}
}
