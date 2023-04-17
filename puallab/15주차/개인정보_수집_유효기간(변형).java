import java.util.*;
import java.io.*;

class Solution {
    static Map<String, Integer> termMap = new HashMap<>();
    static int date;
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        StringTokenizer st;
        date = getDate(today);
        
        for(int i =0; i<terms.length; i++){
            st = new StringTokenizer(terms[i], " ");
            termMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        for(int i =0; i<privacies.length; i++){
            st = new StringTokenizer(privacies[i], " ");
            int prDate = getDate(st.nextToken());
            int addMonth = termMap.get(st.nextToken());
            
            prDate = prDate + addMonth*28;
            
            if(date < prDate) continue;
            answer.add(i+1);
        }
        
        return answer;
    }
    
    static int getDate(String s){
        StringTokenizer st = new StringTokenizer(s , ".");
        int yy = Integer.parseInt(st.nextToken());
        int mm = Integer.parseInt(st.nextToken());
        int dd = Integer.parseInt(st.nextToken());
        
        return yy*12*28 + mm*28 + dd;
    }
}