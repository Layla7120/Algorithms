import java.util.*;

class Job implements Comparable<Job>{
    int index; 
    int duration;
    int start;
    
    Job(int index, int start, int duration){
        this.index = index;
        this.duration = duration;
        this.start = start;
    }
    
    @Override
    public int compareTo(Job o){
        if(this.duration != o.duration){
            return Integer.compare(this.duration, o.duration);
        }
        
        if(this.start != o.start){
            return Integer.compare(this.start, o.start);
        }
        
        return Integer.compare(this.index, o.index);
    }
}

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int time = 0;
        int count = jobs.length;
        
        PriorityQueue<Job> pq = new PriorityQueue<>();
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        int job_idx = 0;
       
        while(job_idx < count){
            while(job_idx < count && jobs[job_idx][0] <= time){
                Job j_new = new Job(job_idx, jobs[job_idx][0], jobs[job_idx][1]);
                pq.add(j_new); 
                job_idx++;
            }
            
            if(!pq.isEmpty()){
                Job j = pq.poll();
                answer += time + j.duration - j.start;
                time += j.duration;
            } else {
                time = jobs[job_idx][0];
            }
        }
        
        while(!pq.isEmpty()){
            Job j = pq.poll();
            answer += time + j.duration - j.start;
            time += j.duration;
        }
        
        return answer / count;
    }
}