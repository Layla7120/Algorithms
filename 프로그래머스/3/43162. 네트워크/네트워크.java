import java.util.*;

class Solution {
    static int[] parent;
    public int solution(int n, int[][] computers) {
        parent = new int[n];
        int answer = n;
        
        for(int i=0; i<n; i++){
            parent[i] = i;
        }
        
        
        for(int i=0; i<computers.length; i++){
            for(int j=0; j<n; j++){
                if(computers[i][j] == 1 && union(i, j)){
                    answer--;
                }
            }
        }
        
        return answer;
    }
    
    public static boolean union(int a, int b){
        int p_a = find(a);
        int p_b = find(b);
        
        if(p_a == p_b) return false;
        if(p_a != p_b){
            if(p_a <= p_b) parent[p_b] = p_a;
            else parent[p_a] = p_b;
        }
        
        return true;
    }
    
    public static int find(int a){
        int root = parent[a];
        while(root != parent[root]) { root = parent[root];}
        while(root != a){
            int next = parent[a];
            parent[a] = root;
            a = next;
        }
        return root;
    }
}