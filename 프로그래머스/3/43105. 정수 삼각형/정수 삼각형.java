import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
    
        for(int i=0; i<triangle.length-1; i++){
            for(int j=0; j<triangle[i].length; j++){
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + triangle[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + triangle[i+1][j+1]);
            }
        }
        
        int last_row = triangle.length-1;
        
        for(int i=0; i<triangle[last_row].length; i++){
            answer = Math.max(answer, dp[last_row][i]);
        }
        
        return answer;
    }
}