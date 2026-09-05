import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int n = order.length;
        int idx = 0;
        int container_box_idx = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        
        while(container_box_idx <= n){
            if(order[idx] == container_box_idx){
                answer++;
                idx++;
                container_box_idx++;
            } else if(!stack.isEmpty() && stack.peek() == order[idx]){
                stack.pop();
                answer++;
                idx++;
            } else {
                stack.push(container_box_idx);
                container_box_idx++;
            }
        }
        
        while(!stack.isEmpty()){
            if(order[idx] == stack.peek()){
                stack.pop();
                answer++;
                idx++;
            } else {
                break;
            }
        }
        
        return answer;
    }
}