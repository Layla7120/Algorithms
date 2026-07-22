import java.util.*;

class Solution {
    
    static int[][] board;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        board = new int[n+1][n+1];
        
        for(int i=0; i<fares.length; i++){
            int c = fares[i][0];
            int d = fares[i][1];
            int f = fares[i][2];
            
            board[c][d] = f;
            board[d][c] = f;
        }
        
        int[] min_fares_s = dijkstra(s, n, fares);
        int[] min_fares_a = dijkstra(a, n, fares);
        int[] min_fares_b = dijkstra(b, n, fares);
        
        int answer = Integer.MAX_VALUE;
        
        for(int i=1; i<=n; i++){
            int cost = min_fares_s[i] + min_fares_a[i] + min_fares_b[i];
            answer = Math.min(answer, cost);
        }
        
        return answer;
    }
    
    public static int[] dijkstra(int start, int n, int[][] fares){
        int[] min_fares = new int[n+1];
        
        for(int i=1; i<=n; i++){
            min_fares[i] = Integer.MAX_VALUE;
        }
        
        min_fares[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        pq.add(new int[]{start, 0});
        
        while(!pq.isEmpty()){
            int[] arr = pq.poll();
            int idx = arr[0], cost = arr[1];
            
            if(cost > min_fares[idx]) continue;
            for(int next=1; next<=n; next++){
                if(board[idx][next] == 0) continue;
                int total = board[idx][next] + cost;
                if(total < min_fares[next]){
                    min_fares[next] = total;
                    pq.add(new int[]{next, total});
                }
            }
        }
        
        return min_fares;
    }
}