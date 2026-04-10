import java.util.*;
import java.io.*;

class Dice {
	int x,y;
	
	// 1: 위, 2: 북, 3: 동, 4: 서, 5: 남, 6:아
	int[] face = {0, 0, 0, 0, 0, 0, 0};
	Dice(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	void move(int dir) {
		
		// 동
		if(dir == 1) {
			int temp = face[1];
			face[1] = face[4];
			face[4] = face[6];
			face[6] = face[3];
			face[3] = temp;
		} else if(dir == 2) {
			int temp = face[1];
			face[1] = face[3];
			face[3] = face[6];
			face[6] = face[4];
			face[4] = temp;
		} else if(dir == 3) {
			int temp = face[1];
			face[1] = face[5];
			face[5] = face[6];
			face[6] = face[2];
			face[2] = temp;
		} else {
			int temp = face[1];
			face[1] = face[2];
			face[2] = face[6];
			face[6] = face[5];
			face[5] = temp;
		}
	}

}

public class Main {
	static int N, M, x, y, K;

	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Dice dice = new Dice(x, y);
		
		st = new StringTokenizer(br.readLine());
		for(int k=0; k<K; k++) {
			int dir = Integer.parseInt(st.nextToken());

			int nx = dice.x + dx[dir];
			int ny = dice.y + dy[dir];
			if(check(nx, ny)) {
				dice.x = nx;
				dice.y = ny;
				dice.move(dir);
				
				if(board[nx][ny] == 0) {
					board[nx][ny] = dice.face[6];
				} else {
					dice.face[6] = board[nx][ny];
					board[nx][ny] = 0;
				}
				System.out.println(dice.face[1]);
			}
		}
	}
	
	public static boolean check(int x, int y) {
		return 0 <= x & x < N && 0 <= y && y < M;
	}
}