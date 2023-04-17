import java.util.*;

class Solution {
    static int deliIdx, deliCnt, pickIdx, pickCnt;
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        init(n, deliveries, pickups);
        while(true){
            if(pickCnt <= 0 && deliCnt <= 0){
                break;
            }
            int distance = Math.max(deliIdx, pickIdx)+1;

            answer += 2*distance;
            //배송 출발
            goDelivery(cap, deliveries);

            //수거 출발
            goPickup(cap, pickups);
        }


        return answer;
    }

    static void goDelivery(int cap, int[] delis){
        // 배송할게 남았나?
        if(deliCnt <= 0) return;

        // cap보다 남은게 많냐?
        if(deliCnt <= cap){
            deliCnt = 0;
            deliIdx = -1;
            Arrays.fill(delis, 0);
            return;
        }

        while(cap > 0 && deliIdx >= 0 ){

            if(delis[deliIdx] >= cap){
                delis[deliIdx] -= cap;
                deliCnt -= cap;
                cap = 0;
            }else{
                cap -= delis[deliIdx];
                deliCnt -=  delis[deliIdx];
                delis[deliIdx] = 0;
            }

            while(deliIdx >= 0 && delis[deliIdx] == 0 ){
                deliIdx--;
            }
        }
    }

    static void goPickup(int cap, int[] picks){
        //수거할게 남았나?
        if(pickCnt <= 0 ) return;

        //이번에 다 수거할 수 있나?
        if(pickCnt <= cap){
            pickCnt = 0;
            pickIdx = -1;
            return;
        }

        //수거
        while(cap >0 && pickIdx >= 0){

            if(picks[pickIdx] >= cap){
                picks[pickIdx] -= cap;
                pickCnt -= cap;
                cap =0;
            }else{
                cap -= picks[pickIdx];
                pickCnt -= picks[pickIdx];
                picks[pickIdx] = 0;
            }

            while(pickIdx >= 0 && picks[pickIdx] == 0 ){
                pickIdx--;
            }

        }

    }

    //초기 값 세팅
    static void init(int n, int[] deliveries, int[] pickups){
        for(int i =0; i<n; i++){
            if(deliveries[i] > 0){
                deliIdx = i;
                deliCnt += deliveries[i];
            }

            if(pickups[i] > 0){
                pickIdx = i;
                pickCnt += pickups[i];
            }
        }
    }

}