import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static long[] A, B, C, D, A_b, C_D;
    static long answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // 정수로 이루어진 크기가 같은 배열
        A = new long[N];
        B = new long[N];
        C = new long[N];
        D = new long[N];
        A_b = new long[N*N];
        C_D = new long[N*N];

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            A[i] = Integer.parseInt(input[0]);
            B[i] = Integer.parseInt(input[1]);
            C[i] = Integer.parseInt(input[2]);
            D[i] = Integer.parseInt(input[3]);
        }

        for(int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                A_b[i*N + j] = A[i] + B[j];
                C_D[i*N + j] = C[i] + D[j];
            }
        }

        Arrays.sort(A_b);
        Arrays.sort(C_D);

        int a = 0;
        int b = C_D.length - 1;
        long total;

        while (a < A_b.length && b >= 0) {
            total = A_b[a] + C_D[b];
            if(total == 0) {
                long A = A_b[a];
                long B = C_D[b];
                long cnt1 = 0;
                long cnt2 = 0;

                while(a < A_b.length && A_b[a] == A) {
                    cnt1++;
                    a++;
                }
                while(b >= 0 && C_D[b] == B) {
                    cnt2++;
                    b--;
                }
                answer += cnt1 * cnt2;
            }
            else if (total < 0) {
                a++;
            }
            else {
                b--;
            }
        }

        System.out.printf("%d", answer);
    }
}