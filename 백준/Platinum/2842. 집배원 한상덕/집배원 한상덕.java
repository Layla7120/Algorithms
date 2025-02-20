import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
	static int N, K_count, x, y;
	static int[] P_loc;
	static char[][] village;
	static ArrayList<Integer> altitude;
	static int[][] alt_map;
	static int left=0, right=0;
	static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
	static int[] dy = {0, 0, 1, -1, -1, 1, 1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		village = new char[N][N];
		P_loc = new int[2];
		alt_map = new int[N][N];
		
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				village[i][j] = line.charAt(j);
				if (line.charAt(j) == 'P') {
					P_loc[0] = i;
					P_loc[1] = j;
				}
				else if(line.charAt(j) == 'K') {
					K_count++;
				}
			}
		}
		
		
		for(int i=0; i<N; i++) {
			String[] new_char = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				int t = Integer.parseInt(new_char[j]);
				set.add(t);
				alt_map[i][j] = t;
			}
		}
		
		// 고도를 일열로 나열해서 정렬
		altitude = new ArrayList<>(set);
		Collections.sort(altitude);
		
		int ans = Integer.MAX_VALUE;
		
		// 투포인터로 점점 키워 나갔다가 줄이기
		while(left <= right && left < altitude.size() && right < altitude.size()) {
			if(BFS()) {
				ans = Math.min(ans, altitude.get(right)-altitude.get(left));
				left++;
			} else {
				right++;
			}
		}
		
		System.out.println(ans);
		
	}
	
	
	private static boolean BFS() {
		int count = 0;
		int[] x_y;
		int[][] visited = new int[N][N];
		Queue<int[]> q = new LinkedList<>();
		
		q.add(P_loc);
		if(alt_map[P_loc[0]][P_loc[1]]> altitude.get(right) || alt_map[P_loc[0]][P_loc[1]] < altitude.get(left))
			return false;
		visited[P_loc[0]][P_loc[1]] = 1;
		
		while (!q.isEmpty()) {
			x_y = q.poll();
			y = x_y[0];
			x = x_y[1];
			
			if (village[y][x] == 'K')
				count++;
			
			if (count == K_count) {
				return true;
			}
			
			for (int i=0; i<8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 영역 조건
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) 
					continue;
				
				// 고도 조건
				if(alt_map[ny][nx] > altitude.get(right) || alt_map[ny][nx] < altitude.get(left))
					continue;
				
				if (visited[ny][nx] == 0) {
					q.add(new int[] {ny, nx});
					visited[ny][nx] = 1;
				}
			}
		}
		
		return false;
	}
}