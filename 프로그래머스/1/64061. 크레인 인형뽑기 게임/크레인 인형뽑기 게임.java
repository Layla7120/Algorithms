import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        
        int[] top_idx = new int[n];
        Arrays.fill(top_idx, n);
        
        for(int c=0; c<n; c++){
            for(int r=0; r<n; r++){
                if(board[r][c] > 0) {
                    top_idx[c] = r;
                    break;
                }
            }
        }
        
        // 시간 복잡도 O(n)
        
        Deque<Integer> deque = new ArrayDeque<>();
        
        for(int i=0; i<moves.length; i++){
            int move_col = moves[i] - 1;
            
            if(top_idx[move_col] >= n) continue;
            
            int doll = board[top_idx[move_col]][move_col];
            top_idx[move_col] = Math.min(n, top_idx[move_col] + 1);
            
            if(deque.isEmpty()) {
                deque.push(doll);
                continue;
            }
            
            int last_doll = deque.pop();
            
            if(doll == last_doll) answer += 2;
            else{
                deque.push(last_doll);
                deque.push(doll);
            }
        }
        
        return answer;
    }
}