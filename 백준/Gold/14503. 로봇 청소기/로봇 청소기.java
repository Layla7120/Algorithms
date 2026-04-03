import java.util.*;
import java.io.*;


public class Main {
	
	static int N, M, r, c, d;
	static int[][] board;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < M; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		
		while(true) {
			if(board[r][c] == 0) {
				board[r][c] = 2;
				answer++;
			} 

			boolean flag = false;
			
			for(int i=1; i<=4; i++) {
				int nd = (d - i + 4) % 4;
				
				int nr = r + dr[nd];
				int nc = c + dc[nd];
				if(check(nr, nc) && board[nr][nc] == 0) {
					r = nr;
					c = nc;
					d = nd;
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				int nr = r + dr[(d + 2) % 4];
				int nc = c + dc[(d + 2) % 4];
				if(check(nr, nc)) {
					r = nr;
					c = nc;
				} else {
					System.out.println(answer);
					return;
				}
			}
		}
		
		
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && board[r][c] != 1;
	}
}
