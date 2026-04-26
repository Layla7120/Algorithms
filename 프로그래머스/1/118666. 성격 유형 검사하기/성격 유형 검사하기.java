import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        
        // R, T, C, F, 
        Map<String, Integer> map = new HashMap<>();
        String[] arr = {"R", "T", "C", "F", "J", "M", "A", "N"};
        
        for(int i=0; i<arr.length; i++){
            map.put(arr[i], 0);
        }
        
        for(int i=0; i<survey.length; i++){
            String s = survey[i];
            int c = choices[i];
            
            if(1 <= c && c <= 3){
                String types = s.charAt(0)+"";
                map.put(types, map.get(types) + 4 - c);
            } else if(5 <= c && c <= 7){
                String types = s.charAt(1)+"";
                map.put(types, map.get(types) + c - 4);
            }
        }
        
        String answer = "";
        for(int i=0; i<7; i+=2){
            int count_0 = map.get(arr[i]);
            int count_1 = map.get(arr[i+1]);
            
            if(count_0 >= count_1){
                answer += arr[i];
            } else {
                answer += arr[i+1];
            }
        }
        
        
        return answer;
    }
}