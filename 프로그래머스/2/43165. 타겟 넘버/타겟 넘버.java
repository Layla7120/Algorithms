class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        choose(0, 0, numbers, target);
        return answer;
    }
    
    public static void choose(int idx, int acc, int[] numbers, int target){
        if(idx == numbers.length){
            if(acc == target)
                answer++;
            return;
        }
        
        choose(idx+1, acc + numbers[idx], numbers, target);
        choose(idx+1, acc - numbers[idx], numbers, target);
    }
}