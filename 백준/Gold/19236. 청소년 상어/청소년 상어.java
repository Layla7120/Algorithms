import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int sdir, answer;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[][] fish;
	static int[][][] grid;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		grid = new int[4][4][2];
		fish = new int[17][2];
		
		answer = 0;
		for(int i=0; i<4; i++) {    
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++){
				grid[i][j][0] = Integer.parseInt(st.nextToken()); //번호
				grid[i][j][1] = Integer.parseInt(st.nextToken()) - 1; //방향
				
				fish[grid[i][j][0]][0] = i;
				fish[grid[i][j][0]][1] = j;
				
			}
		}
		
		// 상어 IN
		sdir = grid[0][0][1];
		int first = grid[0][0][0];
		fish[grid[0][0][0]][0] = -1;
		fish[grid[0][0][0]][1] = -1;
		grid[0][0][0] = -1;
		grid[0][0][1] = -1;
		
		choose(0, 0, sdir, first);
		
		System.out.println(answer);
	}
	
	public static void choose(int sx, int sy, int sdir, int score) {
		int[][][] newGrid = copyGrid(grid);
	    int[][] newFish = copyFish(fish);
	    
		
		// 물고기 움직이기
		for(int i=1; i<17; i++) {    
			if(fish[i][0] > -1) {
				int x = fish[i][0], y = fish[i][1];
				moveFish(x, y, sx, sy);
			}
		}

		// 움직일 수 있는 만큼 움직이고 재귀
		boolean canMove = false;
		for(int step = 1; step <= 3; step++) {
			int tx = sx + dx[sdir] * step;
		    int ty = sy + dy[sdir] * step;
		    if (!inRange(tx, ty)) break;          
		    if (grid[tx][ty][0] > 0) {            
		        canMove = true;
		        break;
		    }
		}
		
		// 상어 움직이기
		if(!canMove) {
			answer = Math.max(answer, score);
			for(int i = 0; i < 4; i++) {
		        for(int j = 0; j < 4; j++) {
		            grid[i][j][0] = newGrid[i][j][0];
		            grid[i][j][1] = newGrid[i][j][1];
		        }
		    }
		    for(int i = 1; i < 17; i++) {
		        fish[i][0] = newFish[i][0];
		        fish[i][1] = newFish[i][1];
		    }
			return;
		}
		
		for (int step = 1; step <= 3; step++) {
		    int tx = sx + dx[sdir] * step;
		    int ty = sy + dy[sdir] * step;
		    if (!inRange(tx, ty)) break;
		    if (grid[tx][ty][0] <= 0) continue;   // 물고기 없는 칸은 패스

		    int num = grid[tx][ty][0];
		    int dir = grid[tx][ty][1];

		    // 먹기
		    grid[tx][ty][0] = -1;
		    grid[tx][ty][1] = -1;
		    int fx = fish[num][0], fy = fish[num][1];
		    fish[num][0] = -1;
		    fish[num][1] = -1;

		    // 재귀
		    choose(tx, ty, dir, score + num);

		    // 롤백(이 분기에서만 바꾼 것들 되돌림)
		    grid[tx][ty][0] = num;
		    grid[tx][ty][1] = dir;
		    fish[num][0] = fx;
		    fish[num][1] = fy;
		}
				
		for(int i = 0; i < 4; i++) {
	        for(int j = 0; j < 4; j++) {
	            grid[i][j][0] = newGrid[i][j][0];
	            grid[i][j][1] = newGrid[i][j][1];
	        }
	    }
	    for(int i = 1; i < 17; i++) {
	        fish[i][0] = newFish[i][0];
	        fish[i][1] = newFish[i][1];
	    }
	}
	
	public static void moveFish(int x, int y, int sx, int sy) {
		int dir = grid[x][y][1];

		for(int d = dir; ; d = (d + 1) % 8) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if(canFishMove(nx, ny, sx, sy)) {
				if(grid[nx][ny][0] > 0) {
					int num = grid[nx][ny][0], odir = grid[nx][ny][1];
					grid[nx][ny][0] = grid[x][y][0];
					grid[nx][ny][1] = d;
					grid[x][y][0] = num;
					grid[x][y][1] = odir;
					fish[grid[nx][ny][0]][0] = nx;
					fish[grid[nx][ny][0]][1] = ny;
					fish[grid[x][y][0]][0] = x;
					fish[grid[x][y][0]][1] = y;
							
				} else {
					grid[nx][ny][0] = grid[x][y][0];
					grid[nx][ny][1] = d;
					fish[grid[x][y][0]][0] = -1;
					fish[grid[x][y][0]][1] = -1;
					grid[x][y][0] = -1;
					grid[x][y][1] = -1;
					fish[grid[nx][ny][0]][0] = nx;
					fish[grid[nx][ny][0]][1] = ny;
				}
				break;
			}
			if(d == (dir + 7) % 8) break;
		}
	}
	
	public static boolean canFishMove(int nx, int ny, int sx, int sy) {
		if(!inRange(nx, ny))
			return false;
		if(nx == sx && ny == sy)
			return false;
		
		return true;
	}
	
	public static boolean canSharkMove(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		if(!inRange(nx, ny))
			return false;
		
		if(grid[nx][ny][0] > 0)
			return true;
		
		return false;
	}
	
	public static boolean inRange(int x, int y) {
		return 0 <= x && x < 4 && 0 <= y && y < 4;
	}
	
	static int[][][] copyGrid(int[][][] original) {
        int[][][] copy = new int[4][4][2];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                copy[i][j][0] = original[i][j][0];
                copy[i][j][1] = original[i][j][1];
            }
        }
        return copy;
    }
	
	static int[][] copyFish(int[][] original) {
        int[][] copy = new int[17][2];
        for(int i = 1; i < 17; i++) {
                copy[i][0] = original[i][0];
                copy[i][1] = original[i][1];
        }
        return copy;
    }
	
	public static void printFish() {
		for(int i=1; i<17; i++) {    
			System.out.println("fish " + i + ":" + fish[i][0] + " " + fish[i][1]);
		}
	}
	
	public static void printShark(int sx, int sy, int sdir) {
		System.out.println("shark " + sx + " " + sy + " " + sdir);
	}
	
	public static void printGrid(int[][][] grid) {
		System.out.println("Grid");
		for(int i=0; i<4; i++) {    
			for(int j=0; j<4; j++){
				System.out.print("[" + grid[i][j][0] + " " + grid[i][j][1] + "]");
			}
			System.out.println();
		}
		
	}
}