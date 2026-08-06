class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int rows = board.length;
        int cols = board[0].length;
        
        int[][] accu = new int[rows+1][cols+1];
        
        for(int i=0; i<skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1], c1 = skill[i][2];
            int r2 = skill[i][3], c2 = skill[i][4];
            int degree = skill[i][5];
            
            
            int value = 1;
            if(type == 1) value = -1;
            
            value *= degree;
            
            accu[r1][c1] += value;
            accu[r1][c2+1] -= value;
            accu[r2+1][c1] -= value;
            accu[r2+1][c2+1] += value;
        }
        
        for(int c=1; c<cols; c++){
            accu[0][c] += accu[0][c-1];
        }
        
        for(int r=1; r<rows; r++){
            accu[r][0] += accu[r-1][0];
        }
        
        for(int r=1; r<rows; r++){
            for(int c=1; c<cols; c++){
                accu[r][c] = accu[r][c] + accu[r-1][c] + accu[r][c-1] - accu[r-1][c-1];
            }
        }
        
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(accu[r][c] + board[r][c] > 0) answer++;
            }
        }
        
        
        
        return answer;
    }
}