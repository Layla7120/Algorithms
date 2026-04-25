import java.util.*;
import java.io.*;

class Solution {
    
    public static int[][] cost_g, hint_g;
    public static int n;
    public static Map<String, Integer> memo;
    
    public int solution(int[][] cost, int[][] hint) {
        int answer = 0;
        
        cost_g = cost;
        hint_g = hint;
        n = cost.length;

        memo = new HashMap<>();
        
        
        return solve(0, new int[n]);
    }
    
    public static int solve(int stage, int[] inventory){
        if(stage == n) return 0;
        
        String key = stage + Arrays.toString(inventory);
        
        if(memo.containsKey(key)) return memo.get(key);
        
        int useHintMinCost = Math.min(inventory[stage], n-1);
        int costs = cost_g[stage][useHintMinCost];
        
        int res = costs + solve(stage + 1, inventory);
        
        if(stage < n - 1){
            int[] newInventory = inventory.clone();
            
            for(int h=1; h<hint_g[stage].length; h++){
                newInventory[hint_g[stage][h]-1]++;
            }
            
            res = Math.min(res, costs + hint_g[stage][0] + solve(stage + 1, newInventory));
        }
        
        memo.put(key, res);
        return res;
    }
    
}