class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        
        int max_head = 0;
        int max_tail = 0;
        
        
        int[] dq_max = new int[stones.length];
        
        for(int i=0; i<stones.length; i++){
            if(dq_max[max_head] <= i - k){
                max_head++;
            }
            
            while(max_head < max_tail && stones[dq_max[max_tail-1]] < stones[i]){
                max_tail--;
            }
            
            dq_max[max_tail++] = i;
            
            if (i >= k - 1) {
                answer = Math.min(answer, stones[dq_max[max_head]]);
            }
            
        }
        return answer;
    }
}