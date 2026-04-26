import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] fees, String[] records) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        int max_time = 23 * 60 + 59;
        
        int default_time = fees[0];
        int default_fee = fees[1];
        double time_unit = fees[2];
        int time_fee = fees[3];
        
        Map<String, int[]> map = new TreeMap<>(); 
        
        for(String record: records){
            String[] arr = record.split(" "); // "05:34 5961 IN"
            
            String[] time_string = arr[0].split(":");
            int time = Integer.parseInt(time_string[0]) * 60 + Integer.parseInt(time_string[1]);
            
            map.putIfAbsent(arr[1], new int[2]);
            
            if(arr[2].equals("IN")){
                map.get(arr[1])[0] = time;
            } else {
                int past = map.get(arr[1])[0];
                map.get(arr[1])[1] += time - past; 
                map.get(arr[1])[0] = 0;
            }
        }
        
        Set<String> map_key = map.keySet();
        
        for(String key: map_key){
            System.out.println(key);
            if(map.get(key)[0] != 0 || map.get(key)[1] == 0){
                map.get(key)[1] += max_time - map.get(key)[0]; 
            }
            int total_time = map.get(key)[1];
            int fee = default_fee;
            if(total_time > default_time)
            {
                fee = default_fee + (int) Math.ceil((total_time - default_time) / time_unit) * time_fee;
            }
            answer.add(fee);
        }
        return answer;
    }
}