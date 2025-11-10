import java.util.*;

class Solution {
    
    static char[][] store;
    static int N, M;
    
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        int answer = N * M;
        
        store = new char[N+2][M+2];
        
        // char 배열로 변경하여 성능 향상
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                store[i][j] = storage[i-1].charAt(j-1);
            }
        }
        
        for(int i=0; i<requests.length; i++){
            String str = requests[i];
            char target = str.charAt(0);
            
            if(str.length() == 2 && str.charAt(0) == str.charAt(1)){
                answer -= getAll(target);
            } else {
                cleanUp();
                answer -= getOutside(target);
            }
        }
        
        return answer;
    }
    
    private static int getOutside(char word){
        int total = 0;
        
        // 배열 복사 제거, 직접 수정
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(store[i][j] == word && check(i, j)){
                    total++;
                    store[i][j] = '0';
                }
            }
        }
        
        return total;
    }
    
    private static void cleanUp(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        boolean[][] visited = new boolean[N+2][M+2];
        
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];
            
            if(visited[x][y]) continue;
            visited[x][y] = true;
            
            if(store[x][y] != 0 && store[x][y] != '0') continue;
            store[x][y] = 0;
            
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                
                if(inRange(nx, ny) && !visited[nx][ny]){
                    if(store[nx][ny] == '0' || store[nx][ny] == 0){
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
    
    private static boolean inRange(int x, int y){
        return 0 <= x && x <= N+1 && 0 <= y && y <= M+1;
    }
    
    private static boolean check(int x, int y){
        for(int i=0; i<4; i++){
            if(store[x+dx[i]][y+dy[i]] == 0){
                return true;
            }
        }
        return false;
    }
    
    private static int getAll(char word){
        int total = 0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(store[i][j] == word){
                    store[i][j] = '0';
                    total++;
                }
            }
        }
        return total;
    }
}