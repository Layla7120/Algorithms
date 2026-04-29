class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int rows = board.length;
        int cols = board[0].length;
        int[][] memo_board = new int[rows+1][cols+1];
        int[][] sum_board = new int[rows+1][cols+1];
        
        for(int i=0; i<skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if(type == 1){
                memo_board[r1][c1] -= degree;
                memo_board[r1][c2+1] += degree;
                memo_board[r2+1][c1] += degree;
                memo_board[r2+1][c2+1] -= degree;
            } else {
                memo_board[r1][c1] += degree;
                memo_board[r1][c2+1] -= degree;
                memo_board[r2+1][c1] -= degree;
                memo_board[r2+1][c2+1] += degree;
            }
        }
        
        // x축 & y축 먼저 처리
        for(int i=0; i<cols; i++){
            if(i == 0){
                board[0][i] = memo_board[0][i] + board[0][i];
                continue;
            }
            memo_board[0][i] += memo_board[0][i-1];
            board[0][i] += memo_board[0][i];
        }
        
        for(int i=0; i<rows; i++){
            if(i == 0){
                continue;
            }
            memo_board[i][0] += memo_board[i-1][0];
            board[i][0] += memo_board[i][0];
        }
        
        for(int i=1; i<rows; i++){
            for(int j=1; j<cols; j++){
                memo_board[i][j] += memo_board[i][j-1] + memo_board[i-1][j] - memo_board[i-1][j-1];
                board[i][j] += memo_board[i][j];
            }
        }
        
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(board[i][j] > 0){
                    answer++;
                }
            }
        }
        
      
        return answer;
    }
}