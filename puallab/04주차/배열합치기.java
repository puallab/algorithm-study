import java.util.*;
import java.io.*;

public class 배열합치기{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        int idx1 = 0, idx2 = 0;

        st= new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<m; i++){
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        for(int i =0; i<n+m; i++){
            if(idx1 == n){
                sb.append(arr2[idx2++] + " ");
                continue;
            }

            if(idx2 == m){
                sb.append(arr1[idx1++] + " ");
                continue;
            }

            if(arr1[idx1] < arr2[idx2]){
                sb.append(arr1[idx1++] + " ");
            }else{
                sb.append(arr2[idx2++] + " ");
            }
        }
        
        System.out.println(sb.toString());
    }
}