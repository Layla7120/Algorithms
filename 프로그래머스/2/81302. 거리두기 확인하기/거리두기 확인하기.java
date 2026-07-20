import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        boolean flag;
        ArrayList<int[]> idxs = new ArrayList<>();
        String[][] board;
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        for(int i=0; i<places.length; i++){
            board = new String[5][5];
            flag = true;
            
            idxs.clear();
                
            for(int j=0; j<places[0].length; j++){
                String[] str_arr = places[i][j].split("");
                for(int k=0; k<str_arr.length; k++){
                    if(str_arr[k].equals("P")){
                        idxs.add(new int[]{j, k});
                    }
                    board[j][k] = str_arr[k];
                }
            }
            
            for(int j=0; j<idxs.size(); j++){
                if(!flag) break;
                
                int[][] visited = new int[5][5];
                Queue<int[]> q = new LinkedList<>();
                
                if(board[idxs.get(j)[0]][idxs.get(j)[1]].equals("P")){
                    q.add(new int[]{idxs.get(j)[0], idxs.get(j)[1], 0});
                    visited[idxs.get(j)[0]][idxs.get(j)[1]] = 1;
                }
                
                while(!q.isEmpty()){
                    int[] new_arr = q.poll();
                    
                    for(int dir=0; dir<4; dir++){
                        int nx = new_arr[0] + dx[dir];
                        int ny = new_arr[1] + dy[dir];
                        
                        if(nx >= 5 || ny >= 5 || nx < 0 || ny < 0)
                            continue;
                        
                        if(visited[nx][ny] == 1)
                            continue;
                        
                        if(board[nx][ny].equals("P") && new_arr[2] < 2){
                            flag = false;
                            break;
                        }

                        if(board[nx][ny].equals("O") && new_arr[2] < 2){
                            q.add(new int[]{nx, ny, new_arr[2]+1});
                            visited[nx][ny] = 1;
                        }
                    }
                }
            }
            if(flag == true)
                answer[i] = 1;
        }
        return answer;
    }
}