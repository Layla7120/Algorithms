class Solution {
    static int N;
    static int[][] tri;
    
    public int solution(int[][] triangle) {
        N = triangle.length;
        tri = triangle;
        
        return Find();
    }
    
    
    private static int Find(){
        int[][] dp = new int[N][N];
        dp[0][0] = tri[0][0];
        
        for(int i=0; i<tri.length-1; i++){
            for(int j=0; j<tri[i].length; j++){
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + tri[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + tri[i+1][j+1]);
            }
        }
        
        int answer = 0;
        for(int i=1; i<tri.length; i++){
            answer = Math.max(answer, dp[tri.length - 1][i]);
        }
        
        return answer;
    }
}