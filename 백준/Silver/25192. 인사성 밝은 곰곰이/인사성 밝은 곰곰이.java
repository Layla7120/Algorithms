import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashMap<String, Boolean> map = new HashMap<>();

        int answer = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            if(line.equals("ENTER")) {
                map = new HashMap<>();
                continue;
            }

            if(!map.containsKey(line)) {
                answer++;
                map.put(line, true);
            }
        }
        System.out.println(answer);
    }
}
