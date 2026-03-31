class Solution {
    public int solution(int[][] signals) {
        int answer = 0;
        for(int i = 1; i < 10000000; i++){
            boolean allTrue = true;
            for(int j=0; j<signals.length; j++){
                int G = signals[j][0], Y = signals[j][1], R = signals[j][2];
                int cycle = G + Y + R;
                
                int time = i % cycle;
                if(G < time && time <= G+Y)
                    allTrue &= true;
                else {
                    allTrue &= false;
                    break;
                    }
            }
            answer++;
            if(allTrue) return answer;
        }
        
        return -1;
    }
}