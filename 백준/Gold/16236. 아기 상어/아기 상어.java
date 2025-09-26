import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class Main{
	static int N, answer, x, y, size, ate_count;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] grid, dp;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		grid = new int[N][N];
		
		x = 0;
		y = 0;
		size = 2;
		ate_count = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if(grid[i][j] == 9) {
					x = i;
					y = j;
					grid[i][j] = 0;
				}
			}
		}
		
		// 상어 위치에 물고기 찾기
		boolean ate = false;
		do {
			ate = findFish();
		} while(ate);
		
		System.out.println(answer);
		
	}
	
	public static void printDp(int[][] grid) {
		System.out.println();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++){
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printXY() {
		System.out.println("XY " + x + " " + y);
	}
	
	public static boolean findFish() {
		Queue<int[]> q = new LinkedList<>();
		int[][] distance = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				distance[i][j] = -1;
			}
		}
		
		q.add(new int[] {x, y});
		distance[x][y] = 0;
		
		ArrayList<int[]> allFish = new ArrayList<>();
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0], cy = curr[1];
			
			if(isFish(cx, cy)) {
				allFish.add(new int[] {cx, cy, distance[cx][cy]});
			}
			
			for(int i=0 ;i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if(inRange(nx, ny) && distance[nx][ny] == -1 && canGo(nx, ny)){
					distance[nx][ny] = distance[cx][cy]+1;
					q.add(new int[] {nx, ny});
				}
			}
		}
			
		if(allFish.isEmpty()) {
			return false;
		}
		
		int minDist = Integer.MAX_VALUE;
		for(int i=0; i<allFish.size(); i++) {
			int[] fish = allFish.get(i);
			int fishDist = fish[2];
			minDist = Math.min(minDist, fishDist);
		}
		
		ArrayList<int[]> closestFish = new ArrayList<>();
		for(int i=0; i<allFish.size(); i++) {
			int[] fish = allFish.get(i);
			if(fish[2] == minDist) {
				closestFish.add(fish);
			}
		}
	    
		Collections.sort(closestFish, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[0] != b[0])
					return a[0] - b[0];
				return a[1] - b[1];
			}
		});
		
		int[] target = closestFish.get(0);
		
	    // 물고기 잡기
	    ate_count++;
	    if (ate_count == size) {
	        size++;
	        ate_count = 0;
	    }
	    
	    grid[x][y] = 0;
	    x = target[0];
	    y = target[1];
	    grid[x][y] = 0;
	    answer += minDist;
	    
	    return true;
	}
	
	public static boolean isFish(int x, int y) {
		return grid[x][y] > 0 && grid[x][y] < size;
	}
	
	public static boolean canGo(int x, int y) {
		int num = grid[x][y];
		if(num == 9)
			return true;
		if(num <= size)
			return true;
		return false;
	}
	
	public static boolean inRange(int x, int y) {
		return 0 <= x && x< N && 0 <= y && y < N;
	}
	
	
}