import java.util.*;

class Solution {
    
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int rows, cols;
    
    public ArrayList<Integer> solution(String[] maps) {
        ArrayList<Integer> answer = new ArrayList<>();
        rows = maps.length;
        cols = maps[0].length();
        char[][] board = new char[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                board[i][j] = maps[i].charAt(j);
            }
        }
        
        boolean[][] visited = new boolean[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(board[i][j] == 'X' || visited[i][j]) continue;
                
                int count = 0;
                Deque<int[]> dq = new ArrayDeque<>();
                dq.add(new int[]{i, j});
                visited[i][j] = true;
                
                while(!dq.isEmpty()){
                    int[] dot = dq.poll();
                    
                    count += Integer.parseInt(board[dot[0]][dot[1]] + "");
                    
                    for(int d=0; d<4; d++){
                        int nr = dot[0] + dr[d];
                        int nc = dot[1] + dc[d];
                        if(check(nr, nc) && board[nr][nc] != 'X' && !visited[nr][nc]){
                            dq.add(new int[]{nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }
                answer.add(count);
            }
        }
        if(answer.size() == 0)
            answer.add(-1);
        
        answer.sort(Comparator.naturalOrder());
        
        return answer;
    }
    
    private boolean check(int r, int c){
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
}