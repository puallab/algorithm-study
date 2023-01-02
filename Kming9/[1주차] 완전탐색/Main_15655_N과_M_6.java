package _1주차_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15655_N과_M_6 {
	
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
		combination(0, 0, new int[M]);
		System.out.println(sb.toString());
	}

	
	private static void combination(int idx, int cnt, int[] picked) {
		if(cnt == M) 
		{
			for(int i=0; i<M; i++)
				sb.append(picked[i]).append(" ");
			
			sb.append("\n");
			return;
		}
		
		for(int i=idx; i<N; i++) {
			picked[cnt] = arr[i];
			combination(i+1, cnt+1, picked);
		}
	}
}
