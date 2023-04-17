import java.util.*;
import java.io.*;
class Solution {
    static int[][] users;
    static int[] emoticons;
    static int[] ans = new int[2];
    public int[] solution(int[][] user, int[] emoticon) {
     
        users = user;
        emoticons = emoticon;
        
        dfs(0, new int[emoticons.length]);
        
        return ans;
    }
    
    static void dfs(int idx, int[] rates){
        if(idx == rates.length){
            getAns(rates);
            return;
        }
        
        for(int i =0; i<4; i++){
            rates[idx] = (i+1)*10;
            dfs(idx+1, rates);
        }
    }
    
    static void getAns(int[] rates){
        //판매액, 가입자 수
        int signUp =0;
        int totalPrice = 0;
        
        for(int i =0; i<users.length; i++){
            int nowPrice =0;
            
            for(int j=0; j<emoticons.length; j++){
                if(users[i][0] > rates[j]) continue;
                nowPrice += (emoticons[j] * (100-rates[j]))/100;
            }
            
            if(nowPrice >= users[i][1]){
                signUp += 1;
            }else{
                totalPrice += nowPrice;
            }
        }
        
        //signUp, price 비교;
        if(ans[0] == signUp){
            if(ans[1] < totalPrice){
                ans[0] = signUp;
                ans[1] = totalPrice;
            }
        }
        else if(ans[0] < signUp){
            ans[0] = signUp;
            ans[1] = totalPrice;
        }
    }
}