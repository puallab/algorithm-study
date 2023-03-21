import java.util.*;
import java.io.*;
public class 구간_합_구하기5{
	static int n, m, k;
	static int[][] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n+1][n+1];
		for(int i =1; i<=n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j =1; j<=n; j++){
				arr[i][j] = arr[i-1][j] + arr[i][j-1] -arr[i-1][j-1] + Integer.parseInt(st.nextToken());
			}
		}
	
		for(int i =0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			sb.append(arr[y2][x2] - arr[y][x2] - arr[y2][x] + arr[y][x]).append("\n"); 
		}
		
		
		System.out.println(sb.toString());
	}
}