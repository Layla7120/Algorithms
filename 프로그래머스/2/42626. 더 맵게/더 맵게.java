import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int answer = 0;
        
        for(int sco: scoville){
            pq.add(sco);
        }
        
        while(!pq.isEmpty()){
            if(pq.size() == 1) {
                if(pq.peek() < K)
                    return -1;
                return answer;
            }
            
            if(pq.peek() >= K)
                return answer;
            
            
            int score = pq.poll() + (pq.poll() * 2);
            answer += 1;
            pq.add(score);
            
        }
        
        return answer;
    }
}