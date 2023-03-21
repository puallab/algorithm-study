import java.util.*;
import java.io.*;

public class 선분_위의_점{
	static int n,m;
	static int[] points;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		points = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<n; i++){
			points[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(points);
		
		for(int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int start = findUp(from);
			int end = findDown(to);
			if(end < 0 || start >= n) sb.append(0).append("\n");
			else sb.append(end-start+1+"\n");
		}
		System.out.println(sb.toString());
	}

	static int findUp(int val){
		int left = 0;
		int ret = n;
		int right = points.length-1;
		while(left <= right){
			int mid = (left+right)/2;
			if(points[mid] == val){
				return mid;
			}else if(points[mid] < val){
				left = mid+1;
			}else{
				right = mid-1;
				ret=  mid;
			}
		}
		return ret;
	}

	static int findDown(int val){
		int left = 0;
		int ret = -1;
		int right = points.length-1;
		while(left <= right){
			int mid = (left+right)/2;
			if(points[mid] == val){
				return mid;
			}else if(points[mid] < val){
				left = mid+1;
				ret = mid;
			}else{
				right = mid-1;
			}
		}
		return ret;
	}
}