import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i =0; i<numbers.length; i++){
            
            String s= Long.toBinaryString(numbers[i]);
            
            int len = s.length();
            int k =1; //2진수 자리수, number보다 큰 2진수 
            for(k = 1; 1<<k < len ; k++); 
            
            //추가해야할 0의 개수 세기
            int additional = (1<<k)-len-1;

            if(additional == 0){
                answer[i] = check(s, 0, false);
            }else{
                answer[i] = check(s, additional, true);
            }
            
        }
        
        return answer;
    }
    
    static int check(String s, int zero, boolean flag){
        
        if(flag){
            
            StringBuilder sb = new StringBuilder();
            for(int i =0; i< zero; i++){
                sb.append("0");
            }
            //앞에다 붙이기
            int ret = check(sb.toString() + s, 0, false);
            
            //뒤에다 붙이기
            if(ret == 0) ret = check(s + sb.toString(), 0, false);
        
            return ret;
        }
        
        //System.out.println(s);
        for(int i =1; i<s.length(); i+=2){
            if(s.charAt(i) =='0') {
                if(s.charAt(i-1) == '1' || s.charAt(i+1) == '1') return 0;
            }
            
        }
        return 1;
    }
}