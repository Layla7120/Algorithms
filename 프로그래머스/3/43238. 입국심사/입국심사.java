import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long low = 1;
        long high = (long) times[0] * n;
        
        while(low < high){
            long pass_people = 0;
            long mid = (low + high) / 2;
            
            for(int i=0; i<times.length; i++){
                pass_people += mid / times[i];
            }
            
            if(pass_people >= n){
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return high;
    }
}