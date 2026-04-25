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
        
        int maxVal = -1;
        int ansR = 0, ansC = 0;
        
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
                
                // 윈도우가 완성된 즉시 최댓값 갱신 (메모리에 배열 C를 쌓지 않음)
                if (r >= h - 1) {
                    int currentMin = R[deque[head]][c];
                    int currentR = r - h + 1;
                    int currentC = c;
                    
                    if (currentMin > maxVal) {
                        maxVal = currentMin;
                        ansR = currentR;
                        ansC = currentC;
                    } else if (currentMin == maxVal) {
                        // Tie-breaking: 행이 작거나, 행이 같으면 열이 작은 것
                        // 세로로 돌기 때문에 필요한 분기
                        if (currentR < ansR || (currentR == ansR && currentC < ansC)) {
                            ansR = currentR;
                            ansC = currentC;
                        }
                    }
                }
            }
        }
        
        
        return new int[]{ansR, ansC};
    }
}