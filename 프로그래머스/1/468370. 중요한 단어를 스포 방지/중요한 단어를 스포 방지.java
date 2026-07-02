import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int n = message.length();
        boolean[] hidden = new boolean[n];
        for (int[] r : spoiler_ranges)
            for (int i = r[0]; i <= r[1]; i++) hidden[i] = true;

        Set<String> spoiler = new HashSet<>();
        Set<String> normal  = new HashSet<>();

        Matcher m = Pattern.compile("\\S+").matcher(message);  // 단어 = 공백 아닌 덩어리
        while (m.find()) {
            boolean sp = false;
            for (int k = m.start(); k < m.end(); k++) if (hidden[k]) sp = true;
            (sp ? spoiler : normal).add(m.group());
        }
        spoiler.removeAll(normal);   // 평문에도 등장한 단어 제거
        return spoiler.size();
    }
}