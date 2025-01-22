import java.util.Arrays;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        Puzzle puzzle = new Puzzle(diffs, times, limit);
        int left = Arrays.stream(diffs).min().getAsInt();
        int right = Arrays.stream(diffs).max().getAsInt();
        int mid = (left + right) / 2;
        long total_time;
        
        while (left < mid){
            total_time = 0;
            for(int i = 0; i < diffs.length; i++){
                total_time += puzzle.getTime(mid, i);
            }
            if(total_time >= limit){
                left = mid;
            }
            else{
                right = mid;
            }
            mid = (left + right) / 2;
        }
        
        
        total_time = 0;
        for(int i = 0; i < diffs.length; i++){
            total_time += puzzle.getTime(left, i);
        }
        if (total_time <= limit)
            return left;
        return right;
    }

    public static class Puzzle{
        int[] diffs;
        int[] times;
        long limit;

        Puzzle(int[] diffs, int[] times, long limit) {
            this.diffs = diffs;
            this.times = times;
            this.limit = limit;
        }

        public int getTime(int level, int idx){
            if (this.diffs[idx] <= level){
                return times[idx];
            }
            else{
                return (this.diffs[idx] - level) * (times[idx] + times[idx - 1]) + times[idx];
            }
        }
    }
}