import java.util.*;
import java.io.*;

public class Main {
	static int R, C, T;
	static ArrayList<Integer> air = new ArrayList<>();
	static int[][] board, board2;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == -1) {
					air.add(i);
				}
			}
		}
		
		int t = 0;
		while(t < T) {
			// 확산
			spread();
			// 청소
			clean();
			t++;
		}
		
		int answer = 0;
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(board[i][j] > 0)
					answer+= board[i][j];
//				System.out.print(board[i][j] + " ");
			}
//			System.out.println();
		}
		System.out.println(answer);
	}
	
	public static void clean() {
		clean_Action(0, -1, 1);
		clean_Action(1, 1, 3);
		// 아래 시계 
	}
	
	public static void clean_Action(int idx, int move, int end) {
		int dir = 0;
		// 위 반시계
		int r = air.get(idx) + dr[dir];
		int c = dc[dir];
		int prev = 0;
		
		while(check(r, c)) {
			int temp = board[r][c];
			board[r][c] = prev;
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(!check(nr, nc)) {
				if(dir == end)
					break;
				dir = (dir + move + 4) % 4;
				nr = r + dr[dir];
				nc = c + dc[dir];
			}
			
			prev = temp;
			r = nr;
			c = nc;
		}
	}
	
	public static void spread() {
		board2 = new int[R][C];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(board[i][j] > 0) {
					int amount = board[i][j] / 5;
					int count = 0;
					for(int k=0; k<4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						
						if(check(nr, nc)) {
							board2[nr][nc] += amount;
							count++;
						}
					}
					board2[i][j] += board[i][j] - amount * count;
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			board[i] = board2[i].clone();
		}
		
		board[air.get(0)][0] = -1;
		board[air.get(1)][0] = -1;
		
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C && !(c == 0 && air.contains(r));
	}
}