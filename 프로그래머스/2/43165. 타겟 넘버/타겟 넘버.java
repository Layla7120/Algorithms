class Solution {
    static int answer, target_g;
    static int[] numbers_g;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        target_g = target;
        numbers_g = numbers;
        
        choose(0, 0);
        
        return answer;
    }
    
    public static void choose(int count, int total){
        if(count == numbers_g.length){
            if(total == target_g)
                answer++;
            return;
        }
        
        choose(count + 1, total+numbers_g[count]);
        choose(count + 1, total-numbers_g[count]);
    }
}