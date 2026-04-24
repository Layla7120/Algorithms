class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] answer = new int[2];
        int[][] T = new int[m][n];
        int[][] R = new int[m][n];
        int[][] C = new int[m][n];
        
        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                T[r][c] = Integer.MAX_VALUE;
            }
        }
        
        for(int i=0; i<drops.length; i++){
            int r = drops[i][0];
            int c = drops[i][1];
            
            T[r][c] = i+1;
        }
        
        
        for(int r=0; r<m; r++){
            int[] deque = new int[n];
            
            int head = 0;
            int tail = 0;
            for (int c = 0; c < n; c++) {
                while(head < tail && T[r][deque[tail-1]] >= T[r][c]){
                    tail--;
                }
                
                deque[tail++] = c;
                
                if(deque[head] <= c - w){
                    head++;
                }
                
                if (c >= w - 1) {
                    R[r][c - w + 1] = T[r][deque[head]];
                }
            }
        }
        
        
        for(int c = 0; c < n; c++){
            int[] deque = new int[m];
            
            int head = 0;
            int tail = 0;
            for (int r=0; r<m; r++) {
                while(head < tail && R[deque[tail-1]][c] >= R[r][c]){
                    tail--;
                }
                
                deque[tail++] = r;
                
                if(deque[head] <= r - h){
                    head++;
                }
                
                if (r >= h - 1) {
                    C[r - h + 1][c] = R[deque[head]][c];
                }
            }
        }
        
        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(C[r][c] > C[answer[0]][answer[1]]){
                    answer = new int[]{r, c};
                }
            }
        }
        
        
        return answer;
    }
}