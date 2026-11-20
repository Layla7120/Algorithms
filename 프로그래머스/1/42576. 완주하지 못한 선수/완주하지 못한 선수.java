import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String complete: completion){
            map.putIfAbsent(complete, 0);
            map.put(complete, map.get(complete) + 1);
        }
        
        for(String participate: participant){
            if(map.get(participate) == null)
                return participate;
            if(map.get(participate) == 0)
                return participate;
            map.put(participate, map.get(participate) - 1);
        }
        
        return answer;
    }
}