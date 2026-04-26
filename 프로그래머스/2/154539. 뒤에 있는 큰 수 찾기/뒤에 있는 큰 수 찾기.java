import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int i=numbers.length - 1; i>=0; i--){
            while(!dq.isEmpty() && dq.peekFirst() <= numbers[i]){
                dq.pollFirst();
            }
            
            if(dq.isEmpty()){
                answer[i] = -1;
            } else {
                answer[i] = dq.peekFirst();
            }
            
            dq.addFirst(numbers[i]);
        }
        return answer;
    }
}