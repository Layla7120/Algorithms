import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        int[] computers_parent = new int[n];
        
        for(int i=0; i<n; i++){
            computers_parent[i] = i+1;
        }
        
        for(int i=0; i<computers.length; i++){
            int parent = n+1;
            
            for(int j=0; j<computers[i].length; j++){
                if(computers[i][j] == 1){
                    parent = Math.min(parent, find_parent(j, computers_parent));
                }
            }
            
            for(int j=0; j<computers[i].length; j++){
                if(computers[i][j] == 1){
                    computers_parent[computers_parent[j]-1] = parent;
                    computers_parent[j] = parent;
                }
            }
            
            for(int k=n-1; k>=0; k--){
                computers_parent[k] = find_parent(k, computers_parent);
            }
        }
        
        System.out.println(Arrays.toString(computers_parent));
        
        Set<Integer> set = new HashSet<>();
        
        for(int i=0; i<n; i++){
            set.add(computers_parent[i]);
        }
        
        return set.size();
    }
    
    public static int find_parent(int idx, int[] computers_parent){
        while(computers_parent[idx] != computers_parent[computers_parent[idx]-1]){
            idx = computers_parent[idx]-1;
        }
        
        return computers_parent[idx];
    }
}