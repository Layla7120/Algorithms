import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long answer = 0;

        st = new StringTokenizer(br.readLine());

        long[] divide_arr = new long[N + 1];
        long[] divider = new long[M];  // M개의 나머지를 저장할 배열

        // 누적합 구하기
        // 나머지 구하기
        for (int i = 1; i <= N; i++) {
            divide_arr[i] = Long.parseLong(st.nextToken()) + divide_arr[i - 1];
            int d = (int) (divide_arr[i] % M);  // 나머지 구하기

            // 나머지가 음수가 될 수는 없으므로, d는 이미 0 <= d < M의 범위에 있음
            divider[d] += 1;

            if (d == 0) {
                answer += 1;  // 나머지가 0인 경우
            }
        }

        // 나머지 별로 가능한 쌍 계산
        for (int i = 0; i < M; i++) {
            if (divider[i] > 1) {
                answer += (divider[i] * (divider[i] - 1)) / 2;  // 조합 계산
            }
        }

        System.out.println(answer);
    }
}
