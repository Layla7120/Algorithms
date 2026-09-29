import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        
        for(int i=n-1; i >= 0; i--){
            
            while(!stack.isEmpty() && prices[i] <= prices[stack.peek()]){
                stack.pop();
            }
            
            answer[i] = stack.isEmpty() ? (n - 1 - i) : (stack.peek() - i);
            stack.push(i);
        }
        
        return answer;
    }
}