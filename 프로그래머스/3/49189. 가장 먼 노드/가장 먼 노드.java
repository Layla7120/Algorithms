import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int[] distance = new int[n+1];
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for(int i=1; i<=n; i++){
            map.put(i, new ArrayList<>());
        }
        
        for(int i=0; i<edge.length; i++){
            int[] arr = edge[i];
            
            map.get(arr[0]).add(arr[1]);
            map.get(arr[1]).add(arr[0]);
        }
        
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{1, 0});
        distance[1] = -1;
        
        while(!dq.isEmpty()){
            int[] arr = dq.poll();
            
            ArrayList<Integer> arrlist = map.get(arr[0]);
            
            for(Integer i : arrlist){
                if(distance[i] == 0){
                    distance[i] = arr[1] + 1;
                    dq.add(new int[]{i, arr[1]+1});
                }
            }
        }
        
        int MaxNum = 0;
        int answer = 0;
        for(int i=1; i<=n; i++){
            if(distance[i] > MaxNum){
                MaxNum = distance[i];
                answer = 1;
            } else if(distance[i] == MaxNum){
                answer++;
            }
        }
        
        return answer;
    }
}