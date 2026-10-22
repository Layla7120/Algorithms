import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int idx = 0;
        
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int i=1; i<=order.length+1; i++){
            if(i<=order.length && i == order[idx]){
                answer++;
                idx++;
            } else {
                 while(!dq.isEmpty()){
                    if(dq.peek() == order[idx]){
                        answer++;
                        idx++;
                        dq.pop();
                    } else {
                        break;
                    }
                }
                if(i <= order.length)
                    dq.push(i);
            }
        }
        
        return answer;
    }
}