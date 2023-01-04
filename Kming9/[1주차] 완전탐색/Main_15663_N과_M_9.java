package _1주차_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_15663_N과_M_9 {
	
	static int N, M;
	static int[] arr;
	static int[] count;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		count = new int[10001];

		int num;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			num = Integer.parseInt(st.nextToken());
			if(count[num] == 0) arr[i] = num;
				
			count[num]++;
		}
		
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
			if(count[arr[i]] == 0) continue;
		
			count[arr[i]]--;
			picked[cnt] = arr[i];
			permutation(cnt+1, picked);
			count[arr[i]]++;
		}
	}
}
