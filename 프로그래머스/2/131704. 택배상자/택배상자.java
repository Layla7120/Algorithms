import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int idx = 0;
        
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int i=1; i<=order.length; i++){
            dq.push(i);
            while(!dq.isEmpty()){
                if(dq.peek() == order[idx]){
                    answer++;
                    idx++;
                    dq.pop();
                } else {
                    break;
                }
            }
        }
        
        return answer;
    }
}