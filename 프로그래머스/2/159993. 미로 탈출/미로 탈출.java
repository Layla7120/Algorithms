// S -> L -> E
// 1. 복잡도: 2*(n^n) 100이니까 한참남음

import java.util.*;

class Solution {
    
    static int rows, cols;
    static int[] start, lever, exit;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] board;
    
    public int solution(String[] maps) {
        int answer = 0;
        rows = maps.length;
        cols = maps[0].length();
        
        start = new int[2];
        lever = new int[2];
        exit = new int[2];
        
        board = new int[rows][cols];
        
        for(int i=0; i<rows; i++){
            String s = maps[i];
            for(int j=0; j<s.length(); j++){
                String c = s.charAt(j) + "";
                mark(c, i, j);
            }
        }
        
        int start_lever = findRoute(start, lever);
        if(start_lever == -1) return -1;
        int lever_exit = findRoute(lever, exit);
        if(lever_exit == -1) return -1;
        
        return start_lever + lever_exit;
    }
    
    public static int findRoute(int[] start, int[] end){
        boolean[][] visited = new boolean[rows][cols];
        
        Deque<int[]> dq = new ArrayDeque<>();
        
        dq.add(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;
        
        while(!dq.isEmpty()){
            int[] arr = dq.poll();
            
            if(arr[0] == end[0] && arr[1] == end[1]){
                return arr[2];
            }
            
            for(int i=0; i<4; i++){
                int nr = arr[0] + dr[i];
                int nc = arr[1] + dc[i];
                
                if(check(nr, nc) && !visited[nr][nc]){
                    dq.add(new int[]{nr, nc, arr[2]+1});
                    visited[nr][nc] = true;
                }
            }
        }
    
        return -1;
    }
    
    public static boolean check(int r, int c){
        return 0 <= r && r < rows && 0 <= c && c < cols && board[r][c] != 0;
    }
    
    public static void mark(String c, int i, int j){
        switch(c){
            case "S":
                start[0] = i;
                start[1] = j;
                board[i][j] = 1;
                break;
            case "E":
                exit[0] = i;
                exit[1] = j;
                board[i][j] = 1;
                break;
            case "L":
                lever[0] = i;
                lever[1] = j;
                board[i][j] = 1;
                break;
            case "O":
                board[i][j] = 1;
        }
    }
}