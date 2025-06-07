import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] mat;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        mat = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int n = Integer.parseInt(st.nextToken());
                mat[i][j] = n;
            }
        }

        int[][] result = power(B);

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                sb.append(result[i][j] % 1000).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] power(long exp) {
        if (exp == 1L) return mat;

        int[][] half = power(exp / 2);
        int[][] res = multiply(half, half);

        if (exp % 2 == 1) {
            res = multiply(res, mat);
        }

        return res;
    }


    private static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum = (sum + (m1[i][k] * m2[k][j]) % 1000) % 1000;
                }
                result[i][j] = sum;
            }
        }

        return result;
    }
}