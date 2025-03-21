import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N, L, curR, curC, curD, answer, start, next;
	static boolean flag = true;
	static int[][] board, dp;		
	
	public static void  main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		board = new int[N+1][N+1];
		dp = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 각 열과 행을 확인하면서
		// 만약 단차가 1차이 이상나면 패스
		// 단차가 1차이 나면
		// - 작은 쪽이 어딘지 파악하고
		// - 그쪽으로 L만큼 단차가 다 같은 지 확인 (범위가 벗어나면 패스)
		
		//행 확인 
		for(int i=1; i<=N; i++) {
			flag = true;
			for(int j=2; j<=N; j++) {
				if(Math.abs(board[i][j] - board[i][j-1]) > 1){
					flag = false;
					break;
				}
				
				if(Math.abs(board[i][j] - board[i][j-1]) == 1){
					
					if(board[i][j] > board[i][j-1]) {
						curD = -1;
						start = j-1;
					} else {
						curD = 1;
						start = j;
					}
					
					next = start;
					if(dp[i][next] == 1) {
						flag = false;
						break;
					}
					dp[i][next] = 1;
					for(int l=1; l<L; l++) {
						next += curD;
						
						if(next < 1 || next > N) {
							flag = false;
							break;
						}
						if(dp[i][next] == 1) {
							flag = false;
							break;
						}
						
						if(board[i][next] != board[i][start]) {
							flag = false;
							break;
						}
						dp[i][next] = 1;
					}
				}
				if(!flag) {
					break;
				}
			}
			
			if(flag) {
				answer += 1;
			}
			
		}
		
		dp = new int[N+1][N+1];
		// 열 확인 
		for(int i=1; i<=N; i++) {
			flag = true;
			for(int j=2; j<=N; j++) {
				if(Math.abs(board[j][i] - board[j-1][i]) > 1){
					flag = false;
					break;
				}
				
				if(Math.abs(board[j][i] - board[j-1][i]) == 1){
					if(board[j][i] > board[j-1][i]) {
						curD = -1;
						start = j-1;
					}
					else {
						curD = 1;
						start = j;
					}
					
					next = start;
					if(dp[next][i] == 1) {
						flag = false;
						break;
					}
					dp[next][i] = 1;
					for(int l=1; l<L; l++) {
						next += curD;
						
						if(next < 1 || next > N) {
							flag = false;
							break;
						}
						if(dp[next][i] == 1) {
							flag = false;
							break;
						}
						if(board[next][i] != board[start][i]) {
							flag = false;
							break;
						}
						dp[next][i] = 1;
					}
				}
				if(!flag) {
					break;
				}
			}
			
			if(flag) {
				answer += 1;
			}
		}
		System.out.println(answer);
	}
}