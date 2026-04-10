import java.util.*;
import java.io.*;

class Point{
	int r, c;
	
	Point(int r, int c){
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int R, C, N;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static boolean[][] visited;
	static char[][] cave;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		R = Integer.parseInt(st.nextToken()); 
		C = Integer.parseInt(st.nextToken()); 
		
		cave = new char[R+1][C+1];
		
		for(int r=1; r<=R; r++) {
			String s = br.readLine();
			for(int c=1; c<=C; c++) {
				cave[r][c] = s.charAt(c-1);
			}
		}
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		for(int n=1; n<=N; n++) {
			boolean hasMoved = true;
			visited = new boolean[R+1][C+1];
			
			int height = R - Integer.parseInt(st.nextToken()) + 1;
			
			boolean crashed = crashAction(n, height);
			
			if(crashed) {
				for(int c=1; c<=C; c++) {
					if(cave[R][c] == 'x') {
						BFS(R, c);
					}
				}
				dropAll();
			}
		}
		
		printCave();
	}
	
	public static void dropAll() {
		
		for(int r=R; r >0; r--) {
			for(int c=1; c<=C; c++) {
				if(cave[r][c] == 'x' && !visited[r][c]) {
					List<Point> fraction = BFS(r, c);
					mark(fraction, '.');
					drop(fraction);
				}
			}
		}
	}
	
	public static boolean drop(List<Point> fraction) {
		
		int minDrop = Integer.MAX_VALUE;
		
		for(Point p: fraction) {
			int dropCount = 0;
			int next_r = p.r + 1;
			
			while(check(next_r, p.c) && cave[next_r][p.c] != 'x') {
				dropCount++;
				next_r++;
			}
			
			minDrop = Math.min(minDrop, dropCount);
		}
		
		if(minDrop == 0) {
			mark(fraction, 'x');
			return false;
		}
		
		for(Point p: fraction) {
			p.r += minDrop;
			visited[p.r][p.c] = true;
		}
		mark(fraction, 'x');
		return true;
	}
	
	public static boolean check(int r, int c) {
		return r > 0 && r <= R && c > 0 && c <= C;
	}
	
	public static List<Point> BFS(int r, int c){
		Deque<Point> q = new ArrayDeque<>();
		q.add(new Point(r, c));
		
		List<Point> arr = new ArrayList<>();
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			arr.add(p);
			
			for(int i=0; i<4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(check(nr, nc) && cave[nr][nc] == 'x' && !visited[nr][nc]) {
					q.add(new Point(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		return arr;
	}
	
	public static void mark(List<Point> arr, char c) {
		for(Point p: arr) {
			cave[p.r][p.c] = c; 
		}
	}
	

	public static boolean crashAction(int n, int height) {
		boolean crashed;
		if(n % 2 == 1) {
			crashed = crash(1, 1, height);
		}
		else {
			crashed = crash(C, -1, height);
		}
		return crashed;
	}
	
	
	public static boolean crash(int start, int gap, int row) {
		for(int i=start; i >= 1 && i <= C; i+=gap) {
			if(cave[row][i] == 'x') {
				cave[row][i] = '.';
				return true;
			}
		}
		return false;
	}
	
	public static void printCave() {
		StringBuilder sb = new StringBuilder();
		
		for(int r=1; r<=R; r++) {
			for(int c=1; c<=C; c++) {
				sb.append(cave[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
}