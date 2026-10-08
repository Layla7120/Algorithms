import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        for(int i=0; i<plans.length; i++){
            plans[i][1] = Integer.toString(getIntTime(plans[i][1]));
        }
        
        Arrays.sort(plans, (a, b) -> Integer.parseInt(a[1]) - Integer.parseInt(b[1]));
        
        
        int answer_idx = 0;
        int next_idx = 1;
        int time = getStartTime(plans[0]);
        String[] inProgress = plans[0];
        boolean flag = true;
        
        Deque<String[]> jobQueue = new ArrayDeque<>();
        
        while (answer_idx < plans.length){
            if(flag && getEndTime(inProgress) <= time){
                answer[answer_idx++] = inProgress[0];
                flag = false;
            }
            
            if(next_idx < plans.length && getStartTime(plans[next_idx]) == time){
                if(flag){
                    jobQueue.push(new String[]{inProgress[0], inProgress[1], Integer.toString(Integer.parseInt(inProgress[2]) - (time - Integer.parseInt(inProgress[1])))});
                }
                inProgress = plans[next_idx++];
                flag = true;
            } else {
                if(flag == false && !jobQueue.isEmpty()){
                    inProgress = jobQueue.pop();
                    inProgress[1] = Integer.toString(time);
                    flag = true;
                }
            }
            time+=1;
        }
        return answer;
    }
    
    public static int getStartTime(String[] plan){
        return Integer.parseInt(plan[1]);
    }
    
    public static int getEndTime(String[] plan){
        return Integer.parseInt(plan[1]) + Integer.parseInt(plan[2]);
    }
    
    public static int getIntTime(String time){
        String[] arr = time.split(":");
        return Integer.parseInt(arr[0])*60 + Integer.parseInt(arr[1]);
    }
}