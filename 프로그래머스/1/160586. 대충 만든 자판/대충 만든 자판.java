import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        Map<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<keymap.length; i++){
            for(int j=0; j<keymap[i].length(); j++){
                String s = keymap[i].charAt(j) + "";
                map.putIfAbsent(s, j+1);
                map.put(s, Math.min(map.get(s), j+1));
            }
        }
        
        for(int i=0; i<targets.length; i++){
            int count = 0;
            for(int j=0; j<targets[i].length(); j++){
                String s = targets[i].charAt(j) + "";
                if(map.containsKey(s)){
                    count += map.get(s);
                } else{
                    count = -1;
                    break;
                }
            }
            answer[i] = count;
        }
        
        return answer;
    }
}