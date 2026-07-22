import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 1;

        int[][] values = new int[N+1][N+1];
        int[] board = new int[N+1];
        
        for(int i=1; i<=N; i++){
            board[i] = Integer.MAX_VALUE;
            for(int j=1; j<=N; j++){
                values[i][j] = Integer.MAX_VALUE;
            }
        }
                                        
        for(int i=0; i<road.length; i++){
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            values[a][b] = Math.min(values[a][b], c);
            values[b][a] = values[a][b];
        }
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        q.add(new int[]{1, 0});
        board[1] = 0;
        
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int now = arr[0], d = arr[1];
            
            if(d > board[now]) continue;
            
            for(int next=1; next<=N; next++){
                if(values[now][next] == Integer.MAX_VALUE) continue;
                int cost = d + values[now][next];
                if(cost < board[next]){
                    board[next] = cost;
                    q.add(new int[]{next, cost});
                }
            }
        }
    
        for(int i=2; i<=N; i++){
            if(board[i] <= K)
                answer++;
        }
        
        return answer;
    }
}