import java.util.*;
import java.io.*;

class FireBall {
	// row, col, m:질량, d:dir, s:speed
	int r, c, m, d, s;
	boolean alive;
	
	FireBall(int r, int c, int m, int s, int d){
		this.r = r;
		this.c = c;
		this.m = m;
		this.d = d;
		this.s = s;
		this.alive = true;
	}
}


public class Main {
	
	static int N, M, K;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static ArrayList<FireBall> allFireBalls;
	static ArrayList<FireBall>[][] grid;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		allFireBalls = new ArrayList<>();
		grid = new ArrayList[N][N];
		
		for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                grid[r][c] = new ArrayList<>();
            }
        }
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			FireBall fb = new FireBall(r-1, c-1, m, s, d);
			allFireBalls.add(fb);
		}
		
		for(int k=0; k<K; k++) {
			for(FireBall fb: allFireBalls) {
					int[] arr = move(fb.r, fb.c, fb.d, fb.s);
					fb.r = arr[0];
					fb.c = arr[1];
					grid[fb.r][fb.c].add(fb);
			}
			
			ArrayList<FireBall> newFireBalls = new ArrayList<>();
			
			for(int r = 0; r < N; r++) {
	            for(int c = 0; c < N; c++) {
	               int size = grid[r][c].size();
	               
	               if(size == 0) continue;
	               
	               if(size == 1) {
	            	   newFireBalls.add(grid[r][c].get(0));
	               } else {
		        	   int mass = 0;
		        	   int speed = 0;
		        	   int dir = 0;
		        	   
		        	   for(FireBall fb : grid[r][c]) {
		                   mass += fb.m;
		                   speed += fb.s;
		                   dir += fb.d % 2;
		               }
		  
		        	   int final_mass = mass / 5;
					   int final_speed = speed / size;
					   
					    if(final_mass > 0) {
							for(int f=0; f<4; f++) {
								int[] dirs;
								if(dir == 0 || dir == size) {
									dirs = new int[] {0, 2, 4, 6};
								} else {
									dirs = new int[] {1, 3, 5, 7};
								} 
								
								FireBall newFireBall = new FireBall(r, c, final_mass, final_speed, dirs[f]);
								newFireBalls.add(newFireBall);
							}
					    }
	               }
	               grid[r][c].clear();
	            }
	        }
			allFireBalls = newFireBalls;
		}
		
		int answer = 0;
		for(FireBall fb: allFireBalls) {
			if(fb.alive)
				answer += fb.m;
		}
		
		System.out.println(answer);
	}
	
	public static void printFBs() {
		for(FireBall fb: allFireBalls) {
			System.out.println("r " + fb.r + " " + "c " + fb.c + " " + fb.d);
		}
		
		System.out.println(allFireBalls.size());
	}
	
	public static int[] move(int r, int c, int d, int s) {
		s %= N;
		int nr = ((r + (dr[d] * s)) + N) % N;
		int nc = ((c + (dc[d] * s)) + N) % N;
		
		return new int[]{nr, nc};
	}
}