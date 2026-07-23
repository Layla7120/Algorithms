class Solution {
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] board = new int[n+1][m+1];
        board[1][1] = 1;
        
        for(int i=0; i<puddles.length; i++){
            int x = puddles[i][0];
            int y = puddles[i][1];
            
            board[y][x] = -1;
        }
        
        for(int x=1; x<=m; x++){
            for(int y=1; y<=n; y++){
                if(board[y][x] != -1){
                    for(int k=0; k<2; k++){
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        
                        if(nx > 0 && nx <= m && ny > 0 && ny <= n && board[ny][nx] != -1){
                            board[ny][nx] = (board[ny][nx] + board[y][x]) % 1000000007;
                        }
                    }
                }
            }
        }
        
        
        
        
        return board[n][m];
    }
}