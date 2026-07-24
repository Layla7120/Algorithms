import java.util.*;

class Solution {
    
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        int low = 1; 
        int high = distance;
        
        Arrays.sort(rocks);

        //n은 적게, answer는 크게 
        while(low < high){
            int delete_count = 0;

            int mid = (low + high + 1) / 2;
            
            int dis = 0;
            
            for(int idx=0; idx<rocks.length; idx++){
                if(rocks[idx] - dis < mid){
                    delete_count++;
                } else {
                    dis = rocks[idx];
                }
            }
            
            if(distance - dis < mid){
                delete_count++;
            } 
            
            if(delete_count <= n){
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        
        
        return low;
    }
}