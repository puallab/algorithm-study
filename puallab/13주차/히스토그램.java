import java.util.*;
import java.io.*;

public class 히스토그램{
    
    static int n, startIdx, k;
    static long area;
    static int[] tree, arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        for(k =1; (1<<k) <=n; k++);
        startIdx = (1<<k);
        tree = new int[4*n];
        for(int i= 0; i<n; i++){
            arr[i] = Integer.MAX_VALUE;
            tree[i + startIdx] = n;
        }
        arr[n] = Integer.MAX_VALUE;
        
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            update(i);
        }
        // 1~ n+1 까지 탐색
        divideSearch(0, n);
        System.out.println(area);
    }

    static void divideSearch(int start, int end){
        if(start == end) return;
        int len = end-start;
        int minIdx = getMinIdx(start, end-1);
        int height = arr[minIdx];
        area = Math.max(area, len*height);
        
        divideSearch(start, minIdx);
        divideSearch(minIdx+1, end);
    }

    static void update(int i){
        int idx = i +startIdx;
        tree[idx] = i;
        int pa = idx/2;
        while(pa > 0){

            // 더 작은 값을 갖는 것을 인덱스로 갖는다.
            int left = pa*2;
            int right =pa*2 + 1;
            tree[pa] = arr[tree[left]] <arr[tree[right]] ? tree[left] : tree[right];
            pa /= 2;
        }
    }

    static int getMinIdx(int left, int right){
        left = left + startIdx;
        right = right + startIdx;
        int retIdx = left;
        while(left < right){

            if(left%2 == 1){
                retIdx = getIdx(left, retIdx);
                left++;
            }
            left/= 2;

            if(right%2 == 0){
                retIdx = getIdx(right, retIdx);
                right--;
            }
            right/=2;
        }
        
        if(left == right){
            retIdx = getIdx(retIdx, left);
        }

        return tree[retIdx];
    }

    static int getIdx(int left, int right){
        // tree의 index를 반환해야하나?
        return arr[tree[left]] < arr[tree[right]] ? left : right;
    }



}