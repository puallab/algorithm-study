import java.util.*;
import java.io.*;
public class 점수따먹기{
	static int n, m;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n+1][m+1];
		for(int i =0; i<n ;i++){
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<m; j++){
				arr[i+1][j+1] = arr[i][j+1] + arr[i+1][j] - arr[i][j] + Integer.parseInt(st.nextToken()); 
			}
		}
		int ans = Integer.MIN_VALUE;
		for(int i= 1; i<=n; i++){
			for(int j =1; j<=m; j++){
				for(int y = 0; y<i; y++){
					for(int x = 0; x<j; x++){
						int val = arr[i][j] - arr[i][x] - arr[y][j] + arr[y][x];
						ans = Math.max(ans, val);
					}
				}
			}
		}

		System.out.println(ans);
		
	}
}