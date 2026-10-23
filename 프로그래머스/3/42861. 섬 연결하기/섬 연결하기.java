import java.util.*;

class Solution {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n+1];
        
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[2], b[2]);
        });
        
        for(int i=0; i<costs.length; i++){
            pq.add(costs[i]);
        }
        
        while(!pq.isEmpty()){
            int[] arr = pq.poll();
            
            int a = arr[0];
            int b = arr[1];
            int cost = arr[2];
            
            if(find(a) == find(b)) continue;
            
            answer += cost;
            union(a, b);
        }
        
        return answer;
    }
    
    public static void union(int a, int b){
        int parent_a = find(a);
        int parent_b = find(b);
        if(a <= b) parent[parent_b] = parent_a;
        else parent[parent_a] = parent_b;
    }
    
    public static int find(int a){
        if(a == parent[a]) return a;
        
        parent[a] = find(parent[a]);
        
        return parent[a];
    }
}