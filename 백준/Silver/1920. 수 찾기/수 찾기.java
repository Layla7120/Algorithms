import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static void match_string(String[] input, String[] target) {
        // 정렬
        List<Integer> intlist = new ArrayList<>();
        for (String i : input) {
            intlist.add(Integer.parseInt(i));
        }
        intlist.sort(Comparator.naturalOrder());

        // 이분 탐색
        for (String t : target) {
            Integer t_i = Integer.parseInt(t);
            Integer start = 0;
            Integer end = intlist.size() - 1; 
            Integer index;

            boolean found = false;
            while (start <= end) {
                index = (start + end) / 2;
                if (t_i.equals(intlist.get(index))) { 
                    System.out.println("1"); 
                    found = true;
                    break;
                } else if (t_i < intlist.get(index)) {
                    end = index - 1;
                } else {
                    start = index + 1;
                }
            }
            if (!found) {
                System.out.println("0"); 
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        String[] input = br.readLine().split(" ");
        String m = br.readLine();
        String[] input2 = br.readLine().split(" ");

        match_string(input, input2);
    }
}
