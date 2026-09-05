import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        int default_time = fees[0];
        int default_fee = fees[1];
        float unit_time = fees[2];
        int unit_fee = fees[3];
        
        Map<String, Deque<Integer>> map = new HashMap<>();
        
        for(int i=0; i<records.length; i++){
            String[] record = records[i].split(" ");
            int time = changeTime(record[0]);
            String car_num = record[1];
            map.putIfAbsent(car_num, new ArrayDeque<>());
            
            map.get(car_num).push(time);
        }
        
        String[] car_nums = map.keySet().toArray(new String[0]);
        Arrays.sort(car_nums);
        int[] answer = new int[car_nums.length];
        
        for(int car=0; car < car_nums.length; car++){
            Deque<Integer> record = map.get(car_nums[car]);
            if(record.size() % 2 != 0) record.push(23*60 + 59);
            int acc_time = 0;
            
            while(!record.isEmpty()){
                int n1 = record.pop();
                int n2 = record.pop();
                
                acc_time += (n1 - n2);
            }
            
            answer[car] = default_fee;
            if(acc_time > default_time){
                answer[car] += Math.ceil((acc_time - default_time)/unit_time + 0.0) * unit_fee;
            }
        }
        return answer;
    }
    
    private int changeTime(String time_S){
        String[] t = time_S.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}