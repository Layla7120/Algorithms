import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<Integer, ArrayList<String>> lengthMap = new HashMap<>();
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        map.clear();
        lengthMap.clear();
        ArrayList<String> answer = new ArrayList<>();
        
        for(String order: orders){
            char[] arr = order.toCharArray();
            
            Arrays.sort(arr);
            
            recursion(0, new StringBuilder(), arr, new HashSet<>());
        }
        
        Object[] arr = map.keySet().toArray();
        Arrays.sort(arr);
        
        for(int i=0; i<course.length; i++){
            int count = course[i];
            ArrayList<String> newarr = lengthMap.getOrDefault(count, new ArrayList<>());
            int maxNum = 2;
            ArrayList<String> target = new ArrayList<>();
            for(String na : newarr){
                int na_count = map.get(na);
                if(map.get(na) > maxNum){
                    maxNum = map.get(na);
                    target.clear();
                    target.add(na);
                } else if(map.get(na) == maxNum){
                    target.add(na);
                }
            }
            answer.addAll(target);
        }
        
        Collections.sort(answer);
        return answer;
    }
    
    public void recursion(int idx, StringBuilder sb, char[] arr, Set<String> visited){
        if(idx == arr.length){
            String str = sb.toString();
            if(visited.contains(str)) return;
            visited.add(str);
            Integer count = map.getOrDefault(str, 0);
            ArrayList<String> arrList = lengthMap.getOrDefault(str.length(), new ArrayList<String>());
            map.put(str, count+1);
            if(!arrList.contains(str)) arrList.add(str);
            lengthMap.put(str.length(), arrList);
            return;
        }
        
        for (int i = idx; i < arr.length; i++) {
            recursion(i+1, sb, arr, visited);
            sb.append(arr[i]);           
            recursion(i+1, sb, arr, visited); 
            sb.deleteCharAt(sb.length() - 1); 
        }
    }
}