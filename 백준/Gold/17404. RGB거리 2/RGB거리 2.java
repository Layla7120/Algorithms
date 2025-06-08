import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, minCost = Integer.MAX_VALUE, INF = 1000 * 1000;
    static int[][] arr;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int c = 0; c < 3; c++){
            dp = new int[N + 1][3][3];

            // 초기화
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        dp[i][j][k] = -1;
                    }
                }
            }

            minCost = Math.min(minCost, arr[0][c] + choose(1, c, c));
        }
        System.out.println(minCost);
    }

    private static int choose(int n, int start, int prior){
        if(n == N){
            return (prior != start) ? 0 : INF;
        }

        if (dp[n][prior][start] != -1)
            return dp[n][prior][start];

        int min = INF;
        for (int i = 0; i < 3; i++) {
            if (i == prior) continue;

            int cost = arr[n][i] + choose(n + 1, start, i);
            min = Math.min(min, cost);
        }

        return dp[n][prior][start] = min;
    }

}