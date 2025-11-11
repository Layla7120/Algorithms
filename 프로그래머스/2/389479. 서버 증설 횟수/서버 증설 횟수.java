import java.util.*;


class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int servers = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<24; i++){
            map.put(i, 0);
        }
        
        for(int i=0; i<24; i++){
            int reduceServer = map.get(i);
            servers -= reduceServer;
            
            int users = players[i];
            
            int needed = checkServerCount(users, servers, m);
            answer += needed;
            servers += needed;
            map.put(i+k, needed);
        }
        
        return answer;
    }
    
    private static int checkServerCount(int users, int servers, int m){
        int neededServer = users / m;
        if(servers >= neededServer) return 0;
        return neededServer - servers;
    }
    
}