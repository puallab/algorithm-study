import java.util.*;
import java.io.*;

public class 두_용액{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int left = 0;
        int right = n-1;
        int sum = arr[left] + arr[right];
        int ans = Integer.MAX_VALUE;
        int le = left, ri= right;

        while(left < right){
            sum = arr[left]+arr[right];
            if(Math.abs(ans) > Math.abs(sum)){
                le = left;
                ri = right;
                ans = Math.abs(sum);
            }

            if(sum == 0){
                break;
            }
            else if(sum < 0){
                left++;
            }else{
                right--;
            }
            
        }

        System.out.println(arr[le] +" " + arr[ri]);
    }
}