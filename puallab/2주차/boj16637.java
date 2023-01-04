import java.io.*;
/*
코드 로직 순서
1. opers 중에 [0~ oper/2] 뽑는 경우의 수 (idx) 구하기
2. 뽑은 위치에 따른 괄호 계산.

계산중 결과값 저장 방식
1.괄호 계산 결과는 앞에 넣고 
2.순서대로 계산 결과는 뒤에 넣기

마지막 결과 값은 nums[nums.length -1] 에 있음.

계산하고 나서 결과값 어떻게 처리?
1) 선택할 때 마다 계산해서 넘겨주기
2) 뽑고나서 한 번에 계산하기 V
*/
public class boj16637{
	static int n, ans = Integer.MIN_VALUE;
	static char[] opers;
	static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String s= br.readLine();
		opers = new char[n/2];
		nums = new int[n/2+1];

		for(int i =0; i<n; i++){
			if(i%2 == 0){
				nums[i/2] = s.charAt(i)-'0';
			}else{
				opers[i/2] = s.charAt(i);
			}
		}

		for(int i =0; i<=opers.length/2; i++){
			dfs(0, 0, i, new int[i]);
		}

		System.out.println(ans);

    }

	static void dfs(int idx, int picked, int n, int[] index){
		if(picked == n){

			ans = Math.max(ans, getCalc(index));
			return;
		}

		for(int i =idx; i<opers.length; i++){
			if(picked > 0 && index[picked-1] == i-1) continue;
			index[picked] = i;
			dfs(i+1, picked+1, n, index);
			index[picked] = -1;
		}
	}

	static int getCalc(int[] index){
		boolean[] isUsed = new boolean[opers.length];
		int[] tempNum = new int[nums.length];
		for(int i =0; i<tempNum.length; i++){
			tempNum[i] = nums[i];
		}
		
		//우선순위 먼저 계산하기
		for(int i :index){
			isUsed[i] = true;
			tempNum[i] = calc(tempNum[i], tempNum[i+1], opers[i]);
		}

		//순서대로 계산
		for(int i =0; i<opers.length; i++){
			if(isUsed[i]){
				tempNum[i+1] = tempNum[i];
			}else{
				tempNum[i+1] = calc(tempNum[i], tempNum[i+1], opers[i]);
			}
		}
		return tempNum[opers.length];
	}

	static int calc(int a, int b, char op){
		int val =0;
		switch (op) {
			case '+':
				val = a + b;
				break;
			case '-':
				val = a - b;
				break;
			case '*':
				val = a * b;
				break;
		}
		return val;
	}
}