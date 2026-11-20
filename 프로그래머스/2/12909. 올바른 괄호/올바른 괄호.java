import java.util.*;

class Solution {
    boolean solution(String s) {
        
        ArrayDeque<String> dq = new ArrayDeque<>();
        for(int i=0; i<s.length(); i++){
            String c = s.charAt(i)+"";
            if(dq.isEmpty()){
                if(c.equals("("))
                    dq.push(c);
                else
                    return false;
            } else {
                if(dq.peek().equals(c)){
                    dq.push(c);
                } else{
                    dq.pop();
                }
            }
        }
        if(dq.isEmpty())
            return true;
        return false;
    }
}