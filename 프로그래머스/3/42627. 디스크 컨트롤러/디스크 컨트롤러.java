import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int time = 0;
        int count = jobs.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[2] != b[2]) return Integer.compare(a[2], b[2]);
            if(a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        int job_idx = 0;
       
        while(job_idx < count){
            while(job_idx < count && jobs[job_idx][0] <= time){
                int[] j_new = new int[]{job_idx, jobs[job_idx][0], jobs[job_idx][1]};
                pq.add(j_new); 
                job_idx++;
            }
            
            if(!pq.isEmpty()){
                int[] j = pq.poll();
                answer += time + j[2] - j[1];
                time += j[2];
            } else {
                time = jobs[job_idx][0];
            }
        }
        
        while(!pq.isEmpty()){
            int[] j = pq.poll();
            answer += time + j[2] - j[1];
            time += j[2];
        }
        
        return answer / count;
    }
}