
import java.util.*;
import java.io.*;


public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N+1][N+1];
		int[][][] dp = new int[3][N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][1][2] = 1;
		
		for(int r=1; r<=N; r++) {
			for(int c=3; c<=N; c++) {
				if(board[r][c] == 1) continue;
				
				dp[0][r][c] = dp[0][r][c-1] + dp[2][r][c-1];
				
				if(r > 1) {
					dp[1][r][c] = dp[1][r-1][c] + dp[2][r-1][c];
				}
				
				if(board[r-1][c] + board[r][c] + board[r][c-1] == 0) {
					dp[2][r][c] = dp[0][r-1][c-1] + dp[1][r-1][c-1] + dp[2][r-1][c-1];
				}
				
			}
		}
		
		int result = dp[0][N][N] + dp[1][N][N] + dp[2][N][N];
		System.out.println(result);
		
	}
	
}