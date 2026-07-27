import java.util.*;

class Solution {
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int row = 0;
    static int col = 0;
    static boolean[][] board;
    
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        int current_r = 0, current_c = 0;
        row = park.length;
        col = park[0].length();
        
        board = new boolean[row][col];
        
        for(int r=0; r<row; r++){
            String s = park[r];
            for(int c=0; c<col; c++){
                char word = s.charAt(c);
                
                if(word == 'X') continue;
                
                if(word == 'S'){
                    current_r = r;
                    current_c = c;
                }
                board[r][c] = true;
            }
        }
        
        for(int i=0; i<routes.length; i++){
            String[] arr = routes[i].split(" ");
            int direction = get_dir_idx(arr[0]);
            int count = Integer.parseInt(arr[1]);
            
            if(moveAvailable(direction, count, current_r, current_c)){
                current_r += dr[direction] * count;
                current_c += dc[direction] * count;
            }
        }
        
        return new int[]{current_r, current_c};
    }
    
    public static boolean moveAvailable(int dir, int count, int r, int c){
        for(int j=0; j<count; j++){
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if(check(nr, nc)){
                r = nr;
                c = nc;
            } else {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean check(int r, int c){
        return r >= 0 && r < row && c >= 0 && c < col && board[r][c] == true;
    }
    
    public static int get_dir_idx(String s){
        switch(s){
            case "N":
                return 0;
            case "S":
                return 1;
            case "W":
                return 2;
            default:
                return 3;
        }
    }
}