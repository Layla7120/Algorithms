// 시간복잡도 bridge_length * truck_weights
// 10^4 * 10^4 = 10^8 1초 안에 가능

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        int total_weight = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int i=0; i<bridge_length; i++){
            dq.add(0);
        }
        
        int idx = 0;
        while(idx < truck_weights.length){
            int w = dq.poll();
            total_weight -= w;
            
            if(total_weight + truck_weights[idx] <= weight){
                dq.add(truck_weights[idx]);
                total_weight += truck_weights[idx];
                idx++;
            } else {
                dq.add(0);
            }
            
            answer++;
        }
        
        return answer + bridge_length;
    }
}