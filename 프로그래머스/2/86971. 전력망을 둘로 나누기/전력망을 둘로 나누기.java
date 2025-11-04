import java.util.*;


class Solution {
    static int[][] dp;
    
    public int solution(int n, int[][] wires) {
        
        dp = new int[n+1][n+1];
        int answer = Integer.MAX_VALUE;
        
        for(int i=0; i<wires.length; i++){
            int a = wires[i][0];
            int b = wires[i][1];
            
            dp[a][b] = 1;
            dp[b][a] = 1;
        }
        
        for(int i=0; i<wires.length; i++){
            int a = wires[i][0];
            int b = wires[i][1];
            
            dp[a][b] = 0;
            dp[b][a] = 0;
            
            int count_a = bfs(a, n);
            int count_b = bfs(b, n);
            
            answer = Math.min(answer, Math.abs(count_a - count_b));
            
            dp[a][b] = 1;
            dp[b][a] = 1;
        }
        
        return answer;
    }
    
    private static int bfs(int a, int n){
        Queue<Integer> q = new LinkedList<>();
        q.add(a);
        
        int[] visited = new int[n+1];
        
        int count = 0;
        
        while(!q.isEmpty()){
            
            int num = q.poll();
            visited[num] = 1;
            
            for(int i=1; i<=n; i++){
                if(dp[num][i] == 1 && visited[i] == 0){
                    q.add(i);
                    count++;
                }
            }
        }
        
        return count;
    }
}