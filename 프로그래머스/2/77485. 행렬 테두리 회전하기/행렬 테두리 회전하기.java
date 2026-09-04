class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] board = new int[rows+1][columns+1];
        
        int count = 1;
        for(int i=1; i<=rows; i++){
            for(int j=1; j<=columns; j++){
                board[i][j] = count;
                count++;
            }
        }
        
        for(int i=0; i<queries.length; i++){
            int r1 = queries[i][0];
            int c1 = queries[i][1];
            int r2 = queries[i][2];
            int c2 = queries[i][3];
            
            int temp = board[r1][c1];
            int minNum = temp;
            
            for(int c=c1+1; c<=c2; c++){
                int num = board[r1][c];
                board[r1][c] = temp;
                temp = num;
                minNum = Math.min(minNum, num);
            }
            
            for(int r=r1+1; r<=r2; r++){
                int num = board[r][c2];
                board[r][c2] = temp;
                temp = num;
                minNum = Math.min(minNum, num);
            }
            
            for(int c=c2-1; c>=c1; c--){
                int num = board[r2][c];
                board[r2][c] = temp;
                temp = num;
                minNum = Math.min(minNum, num);
            }
            
            for(int r=r2-1; r>=r1; r--){
                int num = board[r][c1];
                board[r][c1] = temp;
                temp = num;
                minNum = Math.min(minNum, num);
            }
            
            answer[i] = minNum;
        }
        
        
        return answer;
    }
}