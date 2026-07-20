import java.util.*;

class Solution {
    static class MinQueue{
        Queue<Integer> q = new LinkedList<>();
        int min_val = Integer.MAX_VALUE;
        
        MinQueue(Queue<Integer> q){
            this.q = q;
        }
        
        void add_value(int val){
            this.min_val = Math.min(min_val, val);
            this.q.add(val);
        }
    }
    
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] board = new int[rows+1][columns+1];
        
        int count = 1;
        for(int i=1; i<=rows; i++){
            for(int j=1; j<=columns; j++){
                board[i][j] = count++;
            }
        }
        
        for(int i=0; i<queries.length; i++){
            int start_r = queries[i][0];
            int start_c = queries[i][1];
            int end_r = queries[i][2];
            int end_c = queries[i][3];

            MinQueue minqueue = new MinQueue(new LinkedList<>());
            minqueue.add_value(board[start_r][start_c]);

            // 시계 방향으로 회전
            // 위 ->
            for(int c=start_c+1; c<=end_c; c++){
                minqueue.add_value(board[start_r][c]);
                board[start_r][c] = minqueue.q.poll();
            }

            // 오른쪽 세로 
            for(int r=start_r+1; r<=end_r; r++){
                minqueue.add_value(board[r][end_c]);
                board[r][end_c] = minqueue.q.poll();
            }

            // 아래 <-
            for(int c=end_c-1; c>=start_c; c--){
                minqueue.add_value(board[end_r][c]);
                board[end_r][c] = minqueue.q.poll();
            }

            // 왼쪽 세로
            for(int r=end_r-1; r>=start_r; r--){
                minqueue.add_value(board[r][start_c]);
                board[r][start_c] = minqueue.q.poll();
            }
            
            answer[i] = minqueue.min_val;
        }
        
        return answer;
    }
}