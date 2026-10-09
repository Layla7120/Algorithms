import java.util.*;

class Solution {
    
    static int[][] visited;
    static Map<Integer, Integer> map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int n, m, groups;
    
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        groups = 0;
        visited = new int[n][m];
        
        // land를 BFS로 돌면서 같이 있는 거 계산
        map = new HashMap<>();
        for(int r=0; r<n; r++){
            for(int c=0; c<m; c++){
                if(land[r][c] == 1 && visited[r][c] == 0){
                    BFS(r, c, land);
                }
            }
        }
        
        
        for(int c=0; c<m; c++){
            Set<Integer> set = new HashSet<>();
            for(int r=0; r<n; r++){
                if(land[r][c] == 1){
                    set.add(visited[r][c]);
                }
            }
            
            Integer[] arr = set.toArray(new Integer[0]);
            int count = 0;
            for(Integer num: arr){
                count += map.get(num);
            }
            
            answer = Math.max(answer, count);
        }
        
        return answer;
    }
    
    public static void BFS(int r, int c, int[][] land){
        groups += 1;
        
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{r, c});
        visited[r][c] = groups;
        
        int count = 0;
        
        while(!dq.isEmpty()){
            int[] arr = dq.poll();
            
            count += 1;
            
            for(int i=0; i<4; i++){
                int nr = arr[0] + dr[i];
                int nc = arr[1] + dc[i];
                
                if(check(nr, nc) && visited[nr][nc] == 0 && land[nr][nc] == 1){
                    dq.add(new int[]{nr, nc});
                    visited[nr][nc] = groups;
                }
            }
        }
        
        map.put(groups, count);
    }
    
    public static boolean check(int r, int c){
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}