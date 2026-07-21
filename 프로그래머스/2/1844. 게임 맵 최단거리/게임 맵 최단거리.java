import java.util.*;

class Solution {
    
    static int n, m;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    
    public int solution(int[][] maps) {
        
        n = maps.length;
        m = maps[0].length;
        
        boolean[][] visited = new boolean[n][m];
        int[][] board = new int[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                board[i][j] = Integer.MAX_VALUE;
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        board[0][0] = 1;
        
        while(!q.isEmpty()){
            int[] arr = q.poll();
            
            int r = arr[0];
            int c = arr[1];
            
            for(int i=0; i<4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(check(nr, nc, maps)){
                    if(!visited[nr][nc]){
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                        board[nr][nc] = board[r][c] + 1;
                    }
                }
            }
        }
        
        if(board[n-1][m-1] != Integer.MAX_VALUE) return board[n-1][m-1];
        return -1;
    }
    
    public static boolean check(int r, int c, int[][] maps){
        return r >= 0 && r < n && c >= 0 && c < m && maps[r][c] == 1;
    }
}