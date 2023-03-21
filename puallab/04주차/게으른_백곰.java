import java.io.*;
import java.util.*;

public class 게으른_백곰{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		if(k > 500000) k = 500000;
		int[] arr = new int[1000001];
		for(int i =0; i<n; i++){
			st= new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arr[x] = g;
		}

		long ans = 0;
		long sum = 0;
		for(int i =0; i<2*k+1; i++){
			sum += arr[i];
		}
		ans = sum;
		for(int i =1; i<=1000001-(2*k+1); i++){
			sum = sum+arr[i+2*k]- arr[i-1];
			ans = Math.max(ans, sum);
		}
		System.out.println(ans);
	}
}