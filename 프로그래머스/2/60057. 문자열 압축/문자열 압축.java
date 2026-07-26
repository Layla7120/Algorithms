class Solution {
    public int solution(String s) {
        int string_len = s.length();
        int answer = string_len;
        StringBuilder sb;
       
        
        for(int len=1; len<string_len; len++){
            sb = new StringBuilder();
            int idx = 0;
            int count = 1;
            String sub1 = s.substring(idx, idx+len);
            
            while(idx + len <= string_len){
                sub1 = s.substring(idx, idx+len);
                
                String sub2 = s.substring(idx+len, Math.min(idx + 2*len, string_len));
                
                if(sub1.equals(sub2)){
                    count++;
                    idx = idx+len;
                } else {
                    if(count > 0){
                        if(count > 1) sb.append(Integer.toString(count));
                        sb.append(sub1);
                        idx = idx+len;
                        count = 1;
                    } else{
                        break;
                    }
                }
            }
            
            sb.append(s.substring(idx, string_len));
            answer = Math.min(sb.length(), answer);
        }
        
        return answer;
    }
}