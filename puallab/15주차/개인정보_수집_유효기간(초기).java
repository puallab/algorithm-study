import java.util.*;
import java.io.*;
class Solution {
    static class Pair{
        int year, month, day;
        String type;
        public Pair(int y, int m, int d, String b){
            year = y;
            month = m;
            day = d;
            type = b;
        }

        public void addMonth(int mm){      
            this.year += mm/12;
            mm = mm%12;

            this.month += mm;
            if(this.month > 12){
                this.month -= 12;
                this.year += 1;
            }

        }
    }
    static Map<String, Integer> termMap = new HashMap<>();
    static Pair today;
    static Pair[] prv;
    public List<Integer> solution(String tod, String[] terms, String[] privacies) {

        List<Integer> answer = new ArrayList<>();
        init(tod, terms, privacies);

        for(int i =0; i<prv.length; i++){

            if(isValid(prv[i])) continue;
            answer.add(i+1);
        }

        return answer;
    }

    static boolean isValid(Pair target){

        int termDay = termMap.get(target.type);
        target.addMonth(termDay);

        if(today.year == target.year) {
            if(today.month == target.month){
                if(today.day >= target.day){
                    return false;
                }
            }
            else if(today.month > target.month) return false;
        }
        else if(today.year > target.year) return false;

        return true;
    }

    static void init(String td, String[] terms, String[] pri){
        StringTokenizer st = new StringTokenizer(td, ".");
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        today = new Pair(y, m, d, "c");

        for(int i =0; i<terms.length; i++){
            st = new StringTokenizer(terms[i], " ");
            termMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        prv = new Pair[pri.length];
        for(int i =0; i<pri.length; i++){
            st = new StringTokenizer(pri[i], " ");
            String yyyymmdd = st.nextToken();
            String type = st.nextToken();

            st = new StringTokenizer(yyyymmdd, ".");
            y = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            prv[i] = new Pair(y, m, d, type);
        }

    }
}