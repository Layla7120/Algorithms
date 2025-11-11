import java.util.*;


class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int servers = 0;
        
        int[] expire = new int[50];
        
        for(int i=0; i<24; i++){
            int reduceServer = expire[i];
            servers -= reduceServer;
            
            int users = players[i];
            
            int needed = checkServerCount(users, servers, m);
            answer += needed;
            servers += needed;
            expire[i+k] = needed;
        }
        
        return answer;
    }
    
    private static int checkServerCount(int users, int servers, int m){
        int neededServer = users / m;
        if(servers >= neededServer) return 0;
        return neededServer - servers;
    }
    
}