import java.util.*;
import java.io.*;


public class Main {
	static int N, M, answer = 0, space;

	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};
	static ArrayList<int[]> virus;
	
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		space = 0;
	
		virus = new ArrayList<>();
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++){
				board[r][c] = Integer.parseInt(st.nextToken());
				if(board[r][c] == 2) {
					virus.add(new int[]{r, c});
				}
				if(board[r][c] == 0) {
					space++;
				}
			}
		}
		
		DFS(0, 0);
		System.out.println(answer);
	}
	
	public static int spread(int[][] newboard){
		int count = 0;
		Deque<int[]> q = new ArrayDeque<>();
		for(int i=0; i<virus.size(); i++) {
			q.add(virus.get(i));
		}
		
		while(!q.isEmpty()) {
			int[] v = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = v[0] + dr[i];
				int nc = v[1] + dc[i];
				
				if(check(nr, nc) && newboard[nr][nc] == 0) {
					newboard[nr][nc] = 2;
					q.add(new int[] {nr, nc});
					count++;
				}
			}
		}
		
		return count;
		
	}
	public static int safeplace() {
		int[][] newboard = new int[N][M];
		for(int i=0; i<N; i++) {
			newboard[i] = board[i].clone();
		}
		
		int newInfectedCount = spread(newboard);
		
		int safespace = space - 3 - newInfectedCount;
		
		return safespace;

	}

	
	public static void DFS(int count, int idx) {
		if(count == 3) {
			int safespace = safeplace();
			if(safespace > 0)
				answer = Math.max(answer, safespace);
			return;
		}
		
		for(int i = idx; i < N * M; i++) {
	        int r = i / M;
	        int c = i % M;
	        
			if(board[r][c] == 0) {
				board[r][c] = 1;
				DFS(count + 1, i+1);
				board[r][c] = 0;
			}
		}
	}
	
	public static boolean check(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}