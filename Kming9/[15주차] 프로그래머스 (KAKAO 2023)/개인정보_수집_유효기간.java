import java.util.*;
import java.time.*;
import java.time.format.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayListInteger ansList = new ArrayList();
        
        DateTimeFormatter fm = DateTimeFormatter.ofPattern(yyyy.MM.dd);
        LocalDate curDate = LocalDate.parse(today, fm);
        LocalDate std = curDate.getDayOfMonth() == 28 
             LocalDate.of(curDate.getYear(), curDate.getMonth().plus(1), 1)  curDate.plusDays(1);

        HashMapString, Integer hm = new HashMap();
        for(String t  terms) {
            String[] info = t.split( );
            hm.put(info[0], Integer.parseInt(info[1]));
        }

        int i = 1;
        for (String p  privacies) {
            String[] info = p.split( );
            LocalDate date = LocalDate.parse(info[0], fm).plusMonths(hm.get(info[1]));
            
            if(date.isBefore(std))
                ansList.add(i);
            
            i++;
        }

        return ansList.stream().mapToInt(n - n).toArray();
    }
}
