import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> dlv = new Stack<>();
        Stack<Integer> pu = new Stack<>();

        for (int i = 0; i < n; i++) {
            dlv.push(deliveries[i]);
            pu.push(pickups[i]);
        }

        while (!dlv.isEmpty() && dlv.peek() == 0)
            dlv.pop();

        while (!pu.isEmpty() && pu.peek() == 0)
            pu.pop();

        while (!dlv.isEmpty() || !pu.isEmpty()) {
            int max = Math.max(dlv.size(), pu.size());
            answer += max * 2;

            int dn = cap;
            while(!dlv.isEmpty()) {
                dn -= dlv.pop();
                if(dn < 0) {
                    dlv.push(Math.abs(dn));
                    break;
                }
            }

            int pn = 0;
            while(!pu.isEmpty()) {
                pn += pu.pop();
                if(pn > cap) {
                    pu.push(pn - cap);
                    break;
                }
            }
        }

        return answer;
    }
}