import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int N, M;
    static int[] m, c;
    static int[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 앱의 수
        M = Integer.parseInt(st.nextToken()); // 필요한 메모리
        m = new int[N+1];
        c = new int[N+1];

        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st2.nextToken());
        }

        int MAX = 10001;
        dp = new int[MAX]; // c비용 썼을 때 확보 가능한 메모리 최대

        // 앱을 바깥에서 순회해야지 중복 갱신이 발생하지 않음
        for(int j=0; j<N; j++) {
            for(int i=MAX-1; i>=0; i--) {
                if(i-c[j] >= 0) {
                    dp[i] = Math.max(dp[i], dp[i - c[j]] + m[j]);
                }
            }
        }

        for(int i=0; i<MAX; i++) {
            if(dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}