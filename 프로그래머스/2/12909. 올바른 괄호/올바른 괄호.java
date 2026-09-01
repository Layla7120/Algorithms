class Solution {
    boolean solution(String s) {
        
        int count = 0;
        for(int i=0; i<s.length(); i++){
            
            if(s.charAt(i) == '(')
                count++;
            if(s.charAt(i) == ')'){
                if(count > 0)
                    count--;
                else
                    return false;
            }
        }
        
        if(count > 0)
            return false;
        
        return true;
    }
}