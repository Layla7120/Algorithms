import java.util.*;
import java.util.regex.*;

class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        
        if(!(s.length() == 6 || s.length() == 4))
            return false;
        return s.matches("[0-9]+");
    }
}