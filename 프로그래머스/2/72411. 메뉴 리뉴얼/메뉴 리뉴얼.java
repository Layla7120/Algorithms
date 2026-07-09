import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        
        ArrayList<String> answer = new ArrayList<>();
        
        for(int i=0; i<course.length; i++){
            map.clear();
            int targetLen = course[i];
            
            for(String order: orders){
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                recursion(0, new StringBuilder(), arr, targetLen);
            }
            
            int maxNum = 2;
            ArrayList<String> temp = new ArrayList<>();
            for(String s : map.keySet().toArray(new String[0])){
                int count = map.get(s);
                if(count > maxNum){
                    maxNum = count;
                    temp.clear();
                    temp.add(s);
                } else if(count == maxNum){
                    temp.add(s);
                }
            }
            answer.addAll(temp);
        }
        
        
        Collections.sort(answer);
        return answer;
    }
    
    public void recursion(int idx, StringBuilder sb, char[] arr, int targetLen){
        if(sb.length() == targetLen){
            String str = sb.toString();
            Integer count = map.getOrDefault(str, 0);
            map.put(str, count+1);
            return;
        }
        
        for (int i = idx; i < arr.length; i++) {
            sb.append(arr[i]);           
            recursion(i+1, sb, arr, targetLen); 
            sb.deleteCharAt(sb.length() - 1); 
        }
    }
}