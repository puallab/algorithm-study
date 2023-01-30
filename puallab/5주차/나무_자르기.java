import java.util.*;
import java.io.*;

public class 나무_자르기{
	static int n, m;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st= new StringTokenizer(br.readLine());
		for(int i =0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int left =0;
		int right = arr[n-1];
		int val =0;
		while(left <= right){
			int mid = (left+right)/2;
			long cm = getCm(mid); // 해당 높이에서 잘려진 길이.
			if(cm == m){
				val = mid;
				left = mid+1;
			}else if(cm < m){
				//현재 평균 높이 낮춰야함.
				right = mid -1;
			}else{
				val= mid;
				left = mid+1;
			}
		}
		System.out.println(val);

	}

	static long getCm(int idx){
		long ret = 0;
		for(int i =0; i <arr.length; i++){
			if(arr[i] <= idx) continue;
			ret += arr[i]-idx;
		}
		return ret;
	}
}