import java.util.*;
import java.io.*;

class Point {
	int r, c;
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

}

public class Main {
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static char[][] board;

	static boolean[][] visited = new boolean[12][6];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new char[12][6];
		
		for(int r=0; r<12; r++) {
			String s = br.readLine();
			for(int c=0; c<6; c++) {
				board[r][c] = s.charAt(c);
			}
		}
		
		int combo = 0;
		
		while(true) {
		
			boolean isPopped = popblocks();
			
			// 움직였는지 확인 
			if(!isPopped) {
				break;
			}
			
			
			// 중혁적용 
		    applyGravity();
			
			combo++;
			for(int r=0; r<12; r++) {
				Arrays.fill(visited[r], false);
			}
		}
		
	
		System.out.println(combo);
		
	}
	
	public static void applyGravity() {
		for(int c=0; c<6; c++) {
			int writeIdx = 11;
			for(int r=11; r>=0; r--) {
				if(board[r][c] != '.') {
					board[writeIdx][c] = board[r][c];
					if(writeIdx != r) {
						board[r][c] = '.';
					}
					writeIdx--;
				}
			}
		}
	}
	
	public static boolean popblocks() {
		boolean flag = false;
		for(int r = 0; r<12; r++) {
			for(int c = 0 ; c < 6; c++) {
				if(board[r][c] != '.' && !visited[r][c]) {
					flag = bfsClean(r, c) || flag;
				}
			}
		}
		
		return flag;
	}
	
	public static boolean bfsClean(int r, int c){
		List<Point> arr = new ArrayList<>();
		
		ArrayDeque<Point> q = new ArrayDeque<>();
		Point p = new Point(r, c);
		
		visited[r][c] = true;
		arr.add(p);
		q.add(p);
		
		while(!q.isEmpty()) {
			Point p1 = q.poll();
			for(int i=0; i<4; i++) {
				int nr = p1.r + dr[i];
				int nc = p1.c + dc[i];
				if(check(nr, nc) && !visited[nr][nc] && (board[nr][nc] == board[r][c])) {
					Point newp = new Point(nr, nc);
					q.add(newp);
					arr.add(newp);
					visited[nr][nc] = true;
				}
			}
		}
		
		if(arr.size() >=4 ) {
			for(Point p3 : arr) {
				board[p3.r][p3.c]= '.'; 
			}
			return true;
		}
		return false;
	}
	
	public static boolean check(int r, int c) {
		return r >= 0 && r < 12 && c >= 0 && c < 6;
	}
}