import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<List<Integer>, String> cond = new HashMap<>();

        cond.put(Arrays.asList(1, 3), "A");
        cond.put(Arrays.asList(2, 2), "B");
        cond.put(Arrays.asList(3, 1), "C");
        cond.put(Arrays.asList(4, 0), "D");
        cond.put(Arrays.asList(0, 4), "E");

        String[] answer = new String[3];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0 ; i < 3; i++){
            String[] lst = br.readLine().split(" ");
            int front = 0;
            int back = 0;
            for (String s: lst){
                if (s.equals("0"))
                    front++;
                else back++;
            }
            answer[i] = cond.get(List.of(front, back));
        }

        for (String s : answer){
            System.out.println(s);
        }
    }
}