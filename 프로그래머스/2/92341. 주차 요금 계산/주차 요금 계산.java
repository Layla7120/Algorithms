import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        Map<String, Integer> history = new HashMap<>();
        Map<String, Integer> total_time = new TreeMap<>();
        
        for(String record: records){
            String[] r = record.split(" ");
            int t = changeToMinute(r[0]);
            
            String carNum = r[1];
            
            if(r[2].equals("IN")){
                history.put(carNum, t);
                total_time.putIfAbsent(carNum, 0);
            } else {
                int t_in = history.remove(carNum);
                total_time.put(carNum, (t-t_in) + total_time.get(carNum));
            }
        }
        
        for(String car: history.keySet()){
            int t_in = history.get(car);
            total_time.put(car, (1439-t_in) + total_time.get(car));    
        }
        
        int[] answer = new int[total_time.size()];
        int i=0;
        for(String car: total_time.keySet()){
            int total_fee = getFee(total_time.get(car), fees);
            answer[i++] = total_fee;
        }
        
        return answer;
    }
    
    private static int getFee(int n, int[] fees){
        int BaseTime = fees[0];
        int BaseFee = fees[1];
        int additionalTime = fees[2];
        int additionalFee = fees[3];
        
        if(n <= BaseTime)
            return BaseFee;
        
        return BaseFee + (int)(Math.ceil((double)(n - BaseTime) / additionalTime)) * additionalFee;
    }
    
    private static int changeToMinute(String word){
        String[] arr = word.split(":");
        
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
}
