import java.io.*;

public class 정수_제곱근{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long target = Long.parseLong(br.readLine());
		long left = 1;
		long right = target;
		long ans = target;
		while(left < right){
			long mid = left/2 +right/2;
			if(Math.pow(mid,2) == target){
				ans = mid;
				break;
			}else if(Math.pow(mid,2) <target){
				left = mid+1;
			}else{
				right = mid;
				ans = Math.min(ans, mid);
			}
		}
		System.out.println(ans);
	}
}