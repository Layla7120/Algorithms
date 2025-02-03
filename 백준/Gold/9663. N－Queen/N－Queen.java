import java.io.*;

public class Main {
    static int N;
    static int total;
    static boolean[] col, diag1, diag2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        col = new boolean[N];
        diag1 = new boolean[2 * N];  // ↘ 대각선
        diag2 = new boolean[2 * N];  // ↙ 대각선

        DFS(0);
        System.out.println(total);
    }

    private static void DFS(int queen_row) {
        if (queen_row == N) {
            total++;
            return;
        }

        for (int c = 0; c < N; c++) {
            if (col[c] || diag1[queen_row + c] || diag2[queen_row - c + N]) continue;

            col[c] = diag1[queen_row + c] = diag2[queen_row - c + N] = true;
            DFS(queen_row + 1);
            col[c] = diag1[queen_row + c] = diag2[queen_row - c + N] = false; 
        }
    }
}