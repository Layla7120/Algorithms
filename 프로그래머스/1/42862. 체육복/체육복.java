class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;

        int[] students = new int[n+1];
        
        for(int i: lost){
            students[i] = 1;
            answer--;
        }
        
        for(int i: reserve){
            if(students[i] == 1){
                students[i] = 0;
                answer++;
                continue;
            }
            
            students[i] = 2;
        }
        
        for(int i=1; i<=n; i++){
            if(students[i] == 1){
                if(students[i-1] == 2){
                    answer++;
                    students[i-1] = 0;
                    continue;
                }
                
                if(i+1 <= n && students[i+1] == 2){
                    answer++;
                    students[i+1] = 0;
                }
            }
        }
        
        return answer;
    }
}