import java.util.*;
import java.io.*;
public class 공장{
    static int n, startIdx, lastIdx, k;
    static int[] top;
    static long ans;
    static long[] tree;
    static Map<Integer, Integer> bottom = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        top = new int[n];
        for(k=1; (1<<k) <=n; k++);
        startIdx = 1<<k;
        lastIdx =  startIdx + n -1;
        tree = new long[4*n];

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            top[i] = Integer.parseInt(st.nextToken());        
        }
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            int val = Integer.parseInt(st.nextToken());
            bottom.put(val, i);
        }

        
        for(int i =0; i<n; i++){
            int number = top[i];
            int bottomIdx = bottom.get(number);
            ans += getSum(bottomIdx, n);
            update(bottomIdx, 1);
        }

        System.out.println(ans);
    }

    static void update(int i, int diff){
        int idx = i + startIdx;
        while(idx > 0){
            tree[idx] += diff;
            idx/=2;
        }
    }

    static int getSum(int left, int right){
        left = startIdx + left;
        right = startIdx + right;
        int ret = 0;
        while(left < right){
            if(left%2 == 1){
                ret += tree[left];
                left++;
            }
            left /= 2;

            if(right%2 == 0){
                ret += tree[right];
                right--;
            }
            right /= 2;
        }

        if(left == right){
            ret += tree[left];
        }

        return ret;
    }
}