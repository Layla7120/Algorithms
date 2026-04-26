import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        String[] today_arr = today.split("\\.");
        int today_year = Integer.parseInt(today_arr[0]);
        int today_month = Integer.parseInt(today_arr[1]);
        int today_day = Integer.parseInt(today_arr[2]);
        int today_i = (today_year * 12 * 28) + (today_month * 28) + today_day;
            
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<terms.length; i++){
            String[] s = terms[i].split(" ");
            
            map.put(s[0], Integer.parseInt(s[1]));
        }
        
        for(int i=0; i<privacies.length; i++){
            String[] s = privacies[i].split(" ");
            String[] date_s = s[0].split("\\.");
            
            int year = Integer.parseInt(date_s[0]);
            int month = Integer.parseInt(date_s[1]);
            int day = Integer.parseInt(date_s[2]);
            int term_month = map.get(s[1]);
            
            int expiring = (year * 12 * 28) + ((month + term_month) * 28) + day;
                
            if(expiring <= today_i){
                answer.add(i+1);
            }
            
        }
        
        return answer;
    }
}