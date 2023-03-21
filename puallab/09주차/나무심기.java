import java.util.*;
import java.io.*;

public class 나무심기{
	static int n, startIdx, size = (int)1e5* 2, MOD = (int)1e9 + 7;
	static long ans = 1;
	static long[] cntTree, sumTree;
	static Set<Integer> set = new HashSet<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int k = 1;
		for(k =1; 1<<k <=size; k++);
		startIdx = 1<<k;
		cntTree = new long[size*4];
		sumTree = new long[size*4];
	
		int first = Integer.parseInt(br.readLine());
		updateCnt(first, 1);
		updateSum(first, first);
		set.add(first);
		for(int i =0; i<n-1; i++){
			int q = Integer.parseInt(br.readLine());
			set.add(q);
			long leftCnt = getCnt(0, q-1);
			long leftLen = getLen(0, q-1);
			long leftVal = (long)q*leftCnt- leftLen;
			
			long rightCnt = getCnt(q+1, size);
			long rightLen = getLen(q+1, size);
			long rightVal = rightLen - (long)q*rightCnt;
			
			long val = (rightVal+leftVal)%MOD;
			updateCnt(q, 1);
			updateSum(q, q);
			if(val == 0) continue;
			ans = ((ans%MOD) * val)%MOD;

			
		}
		if(set.size() == 1) System.out.println(0);
		else System.out.println(ans);
		
	}

	static void updateCnt(int idx, int val ){
		idx = idx + startIdx ;
		while(idx > 0){
			cntTree[idx] += val;
			idx/= 2;
		}
	}

	static long getCnt(int left, int right){
		left = left +startIdx;
		right = right + startIdx;
		long val = 0;
		while(left < right){

			if(left%2 == 1){
				val += cntTree[left];
				left++;
			}
			left /= 2;

			if(right%2 == 0){
				val += cntTree[right];
				right--;
			}
			right /= 2;
		}

		if(left == right){
			val += cntTree[left];
		}

		return val;
	}

	static void updateSum(int idx, int val){
		idx = idx + startIdx;
		while(idx > 0){
			sumTree[idx] += val;
			idx/= 2;
		}
	}

	static long getLen(int left, int right){
		left = left + startIdx;
		right = right +startIdx;
		long val =0;
		while(left < right){
			if(left %2 == 1){
				val += sumTree[left];
				left++;
			}
			left /= 2;

			if(right %2 == 0){
				val += sumTree[right];
				right--;
			}
			right /= 2;
		}

		if(left == right){
			val += sumTree[left];
		}

		return val;
	}

}