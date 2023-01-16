import java.util.*;
import java.io.*;

public class 다이어트{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		int left = 1, right =2;
		while(true){
			int gap = right*right - left*left;
			if(right*right - (right-1)*(right-1)  > n) break;
			if(gap ==n ) sb.append(right).append("\n");
			if(gap <n) right++;
			else left++;
		}
		if(sb.length() == 0) sb.append(-1);
		System.out.println(sb.toString());
	}
}