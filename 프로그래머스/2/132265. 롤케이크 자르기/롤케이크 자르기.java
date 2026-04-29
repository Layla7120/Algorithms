import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int[] count_front = new int[topping.length];
        int[] count_end = new int[topping.length];
        
        Set<Integer> s = new HashSet<>();
        
        
        for(int i=0; i<topping.length; i++){
            s.add(topping[i]);
            count_front[i] = s.size();
        }
        
        s = new HashSet<>();
        for(int i=topping.length-1; i>=0; i--){
            s.add(topping[i]);
            count_end[i] = s.size();
        }
        
        for(int i=0; i<topping.length-1; i++){
            if(count_front[i] == count_end[i+1]){
                answer++;
            }
        }
        
        return answer;
    }
}