import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, answer;
    static ArrayList<Integer> chosen;
    static Map<String, Boolean> map;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        chosen = new ArrayList<>();
        map = new HashMap<>();

        // 완전 탐색
        choose(0, 0);

        System.out.println(answer);
    }

    private static void choose(int idx, int count){
        if(idx == N || count == N/2){
            if(count == N/2) {
                int result_chosen = calculate(chosen);
                int result_not_chosen = calculate(getNotChosen(chosen));

                int result = Math.abs(result_chosen - result_not_chosen);
                answer = Math.min(answer, result);
            }
            return;
        }

        for(int i=idx; i<N; i++) {
            chosen.add(i);
            choose(i + 1, count+1);
            chosen.remove(chosen.size()-1);
        }
    }

    // 능력치 계산
    private static int calculate(ArrayList<Integer> teamMember){
        int total = 0;
        for(int i=0; i<teamMember.size(); i++) {
            for(int j=i+1; j<teamMember.size(); j++) {
                int a = teamMember.get(i);
                int b = teamMember.get(j);

                total += matrix[a][b];
                total += matrix[b][a];
            }
        }
        return total;
    }

    private static ArrayList<Integer> getNotChosen(ArrayList<Integer> teamMember){
        ArrayList<Integer> notChosen = new ArrayList<>();

        for(int i=0; i< N; i++){
            if(teamMember.contains(i))
                continue;
            notChosen.add(i);
        }

        return notChosen;
    }
}
