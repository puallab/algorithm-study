import java.io.*;

public class 정수_제곱근{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	long a = Long.parseLong(br.readLine());
        	long left =0;
        	long right = a;
        	long ans = a;
        	while(left <= right){
            		long mid = (left+right)/2;
            		double target = (double)mid * (double)mid;
            		if(target >= a ){
                	ans = mid;
                	right = mid - 1;
            	}else{
                	left = mid + 1;
            	}
           
        	System.out.println(ans);
	}
}
