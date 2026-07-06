class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        String total = "";
        for(Integer i=0; i<m*t; i++){
            total += Integer.toString(i, n);
        }
        
        int count = 0;
        
        for(int idx=0; idx<total.length(); idx++){
            if(idx%m == (p-1)){
                answer += total.substring(idx, idx+1);
                count++;
            }
            if(count == t)
                break;
        }
        
        return answer.toUpperCase();
    }
}