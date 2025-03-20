import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N, K, L;
	static int row, col, count, time;
	static String direction;
	static int curR = 1, curC = 1, endR = 1, endC = 1, curD = 0;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] board, track;
	
	public static void main(String args[]) throws IOException {

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		board = new int[N+1][N+1];
		track = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				track[i][j] = -1;
			}
		}
		
		StringTokenizer st; 
		// 사과의 위치(행 열)
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			
			board[row][col] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		
		// 매초 마다 일련의 과정을 수행하고,
		
		// 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
		// 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
		// 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
		// 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
		
		// 게임이 끝났는지 확인하고
		// 다음 수행하기
		
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			
			count = Integer.parseInt(st.nextToken());
			direction = st.nextToken();
			
			for(int j=time; j<count; j++) {
				if(!move())
					return;
			}
		
			if(direction.equals("D")) {
				curD += 1;
				
			} else {
				curD += 3;
			}
			curD %= 4;
			
//			System.out.println();
//			for(int k=1; k<=N; k++) {
//				for(int t=1; t<=N; t++) {
//					System.out.print(track[k][t] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		
		// 마지막 움직임
		while(true) {
			if(!move())
				return;
			
		}
		
	}
	
	static boolean move() {
		track[curR][curC] = curD;
		curR += dy[curD];
		curC += dx[curD];
		
		if(!check()) {
			System.out.println(time + 1);
			return false;
		}
		
		if(board[curR][curC] == 1) {
			board[curR][curC] = 0;
		} else {
			int pastdir = track[endR][endC];
			track[endR][endC] = -1;
			endR += dy[pastdir];
			endC += dx[pastdir];
		}
		
		time++;
		
//		System.out.println(time);
//		for(int k=1; k<=N; k++) {
//			for(int t=1; t<=N; t++) {
//				System.out.print(track[k][t] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
		return true;
	}
	
	static boolean check() {
		return 0 < curR && curR <= N && 0 < curC && curC <= N && track[curR][curC] == -1;
	}
}