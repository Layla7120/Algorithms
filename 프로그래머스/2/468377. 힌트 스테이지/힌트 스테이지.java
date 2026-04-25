import java.util.*;
import java.io.*;

class Solution {
    static int[][] cost_g, hint_g;
    static int n;
    static Map<String, Integer> memo;
    
    public int solution(int[][] cost, int[][] hint) {
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
        
        int minHint = Math.min(n-1, inventory[stage]);
        int minCost = cost_g[stage][minHint];
        
        int res = minCost + solve(stage + 1, inventory);
        
        int[] new_inventory = inventory.clone();
        if(stage < n-1){
            for(int h=1; h<hint_g[stage].length; h++){
                new_inventory[hint_g[stage][h]-1]++;
            }

            res = Math.min(res, minCost + hint_g[stage][0] + solve(stage + 1, new_inventory));
        }
        
        memo.put(key, res);
        return res;
    }
}