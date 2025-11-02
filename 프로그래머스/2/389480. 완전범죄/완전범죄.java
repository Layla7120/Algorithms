class Solution {
    static int N =0;
    static int answer;
    static int[][][] dp;
    
    public int solution(int[][] info, int n, int m) {
        answer = Integer.MAX_VALUE;
        
        N = info.length;
        dp = new int[N+1][120][120];
        
        choose(0, 0, 0, n, m, info);
        if(answer == Integer.MAX_VALUE) answer = -1;
        return answer;
    }
    
    
    private void choose(int count, int a, int b, int n, int m, int[][] info) {
        if(dp[count][a][b] == 1)
            return;
        
        if(count == N){
            answer = Math.min(answer, a);
            return;
        }
        
        if(a + info[count][0] < n) {
            choose(count + 1, a + info[count][0], b, n, m, info);
            dp[count+1][a+info[count][0]][b] = 1;
        }
        if(b + info[count][1] < m) {
            choose(count + 1, a, b + info[count][1], n, m, info);
            dp[count+1][a][b + info[count][1]] = 1;
        }
    }
}