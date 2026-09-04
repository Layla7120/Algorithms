import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        
        int[] prev = new int[n+2];
        int[] next = new int[n+2];
        
        for(int i=0; i<n+2; i++){
            prev[i] = i-1;
            next[i] = i+1;
        }
        
        k += 1;
        
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int i=0; i<cmd.length; i++){
            String[] s = cmd[i].split(" ");
            if(s.length == 2){
                int count = Integer.parseInt(s[1]);
                if(s[0].equals("U")){
                    for(int c=0; c<count; c++){
                        k = prev[k];
                    }
                } else {
                    for(int c=0; c<count; c++){
                        k = next[k];
                    }
                }
            } else if(s[0].equals("C")){
                dq.push(k);
                next[prev[k]] = next[k];
                prev[next[k]] = prev[k];
                if(next[k] == n+1)
                    k = prev[k];
                else
                    k = next[k];
            } else {
                int num = dq.pop();
                next[prev[num]] = num;
                prev[next[num]] = num;
            }
        }
        
        boolean[] arr = new boolean[n+1];
    
        int prior = k;
        while(prior >= 0){
            arr[prior] = true;
            prior = prev[prior];
        }
        
        while(k <= n){
            arr[k] = true;
            k = next[k];
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=1; i<=n; i++){
            if(arr[i])
                sb.append("O");
            else
                sb.append("X");
        }
        
        return sb.toString();
    }
}