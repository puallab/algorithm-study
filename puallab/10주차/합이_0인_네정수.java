import java.util.*;
import java.io.*;

public class 합이_0인_네정수 {
    static int n;
    static int[] arr1, arr2, arr3, arr4;
    static long[] aArr, bArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        arr2 = new int[n];
        arr3 = new int[n];
        arr4 = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr1[i] = Integer.parseInt(st.nextToken());
            arr2[i] = Integer.parseInt(st.nextToken());
            arr3[i] = Integer.parseInt(st.nextToken());
            arr4[i] = Integer.parseInt(st.nextToken());
        }

        aArr = new long[n*n];
        bArr = new long[n*n];
        int idx = 0;
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                aArr[idx] = (long)arr1[i] + arr2[j];
                bArr[idx++] = (long)arr3[i] + arr4[j];
            }
        }

        Arrays.sort(aArr);
        Arrays.sort(bArr);
        long cnt = 0;
        for(int i = 0; i<n*n; i++){
            long val = aArr[i];

            int leftIdx = findLeftIdx(-val);
            int rightIdx = findRightIdx(-val);   
            if(leftIdx < rightIdx)
                cnt += rightIdx - leftIdx -1;

        }
        System.out.println(cnt);
    }

    static int findLeftIdx(long val){
        int left = 0;
        int right = n*n-1;
        int idx = n*n;
        while(left <= right){
            int mid = (left+right)/2;
            if(val > bArr[mid]){
                idx = mid;
                left = mid +1;
            }else{
                right = mid -1;
            }
        }
        return idx;
    }

    static int findRightIdx(long val){
        int left = 0;
        int right = n*n-1;
        int idx = -1;
        while(left <= right){
            int mid = (left+right)/2;
            if(val < bArr[mid]){
                idx = mid;
                right = mid -1;
            }else{
                left = mid +1;
            }
        }
        return idx;
    }
}