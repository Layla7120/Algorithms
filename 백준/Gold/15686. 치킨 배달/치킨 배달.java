import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer = Integer.MAX_VALUE;
    static ArrayList<int[]> houses = new ArrayList<>();
    static ArrayList<int[]> shops = new ArrayList<>();
    static ArrayList<int[]> distances = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    houses.add(new int[]{i, j});
                } else if (x == 2) {
                    shops.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < shops.size(); i++) {
            int[] arr = new int[houses.size()];
            for (int j = 0; j < houses.size(); j++) {
                arr[j] = Math.abs(houses.get(j)[0] - shops.get(i)[0])
                        + Math.abs(houses.get(j)[1] - shops.get(i)[1]);
            }
            distances.add(arr);
        }

        choose(0, 0, new ArrayList<>());
        System.out.println(answer);
    }

    private static void choose(int start, int count, ArrayList<Integer> selected) {
        if (count == M) {
            int total = 0;
            for (int i = 0; i < houses.size(); i++) {
                int min = Integer.MAX_VALUE;
                for (int shopIdx : selected) { // 선택된 치킨집만 확인
                    min = Math.min(min, distances.get(shopIdx)[i]);
                }
                total += min;
            }
            answer = Math.min(answer, total);
            return;
        }

        // 변경된 부분: 중복 선택 방지 위해 start부터 시작
        for (int i = start; i < shops.size(); i++) {
            selected.add(i);
            choose(i + 1, count + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }
}
