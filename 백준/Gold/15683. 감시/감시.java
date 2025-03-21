import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
	static int N, M, answer = Integer.MAX_VALUE;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<int[]> cctv;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		cctv = new ArrayList<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] > 0 && board[i][j] < 6) {
					cctv.add(new int[] {i, j});
				}
			}
		}
		
		find(0, board);
		System.out.println(answer);
	}
	
	static void find(int count, int[][] dp) {
		if(count == cctv.size()) {
			answer = Math.min(answer, checkRoom(dp));
			return;
		}
		
		int[] location = cctv.get(count);
		int cctvStyle = dp[location[0]][location[1]];
		
		switch(cctvStyle) {
			case 1:
				for(int i=0; i<4; i++) {
					fill(location[0], location[1], i, dp);
					find(count+1, dp);
					
					revoke(location[0], location[1], i, dp);
				}
				break;
			case 2:
				for(int i=0; i<2; i++) {
					fill(location[0], location[1], i, dp);
					fill(location[0], location[1], i+2, dp);
					find(count+1, dp);
					
					revoke(location[0], location[1], i, dp);
					revoke(location[0], location[1], i+2, dp);
				}
				break;
			case 3:
				for(int i=0; i<4; i++) {
					fill(location[0], location[1], i, dp);
					fill(location[0], location[1], i+1, dp);
					find(count+1, dp);
					
					revoke(location[0], location[1], i, dp);
					revoke(location[0], location[1], i+1, dp);
				}
				break;
			case 4:
				for(int i=0; i<4; i++) {
					fill(location[0], location[1], i, dp);
					fill(location[0], location[1], i+1, dp);
					fill(location[0], location[1], i+2, dp);
					find(count+1, dp);
					
					revoke(location[0], location[1], i, dp);
					revoke(location[0], location[1], i+1, dp);
					revoke(location[0], location[1], i+2, dp);
				}
				break;
			case 5:
				for(int i=0; i<4; i++) {
					fill(location[0], location[1], i, dp);
				}
				find(count+1, dp);
				
				for(int i=0; i<4; i++) {
					revoke(location[0], location[1], i, dp);
				}
		}
		
	}
	
	static void fill(int row, int col, int direction, int[][] dp) {
		int nextRow = row, nextCol = col;
		while(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
			if(dp[nextRow][nextCol] == 6)
				break;
			if(dp[nextRow][nextCol] <= 0)
				dp[nextRow][nextCol] -= 1;
			nextRow += dy[direction % 4];
			nextCol += dx[direction % 4];
		}
	}
	
	static void revoke(int row, int col, int direction, int[][] dp) {
		int nextRow = row, nextCol = col;
		while(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
			if(dp[nextRow][nextCol] == 6)
				break;
			if(dp[nextRow][nextCol] < 0)
				dp[nextRow][nextCol] += 1;
			nextRow += dy[direction % 4];
			nextCol += dx[direction % 4];
		}
	}
	
	static int checkRoom(int[][] dp) {
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(dp[i][j] == 0) {
					count += 1;
				}
			}
		}
		
		return count;
	}
}