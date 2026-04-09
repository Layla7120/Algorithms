import java.util.*;
import java.io.*;

class Shark {
	// (r, c) 위치, s 속력, d 이동방향, z 크
	int r, c, s, d, z;
}

public class Main {
	static int R, C, M;
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1};
	
	static int[][] ocean;
	
	static List<Shark> alive;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // 가로 
		C = Integer.parseInt(st.nextToken()); // 세로 
		M = Integer.parseInt(st.nextToken()); // 상어 수 
		
		ocean = new int[R+1][C+1];
		alive = new ArrayList<>();
		
		for(int m=1; m<=M; m++) {
			st = new StringTokenizer(br.readLine());
			Shark s = new Shark();
			s.r = Integer.parseInt(st.nextToken());
			s.c = Integer.parseInt(st.nextToken());
			s.s = Integer.parseInt(st.nextToken());
			s.d = Integer.parseInt(st.nextToken());
			s.z = Integer.parseInt(st.nextToken());
			alive.add(s);
			ocean[s.r][s.c]= m; 
		}
		
		
		int man_c = 0;
		int answer = 0;
		
		while(man_c < C) {
			man_c += 1;
			for(int r=1; r<=R; r++) {
				if(ocean[r][man_c] > 0) {
					answer += alive.get(ocean[r][man_c]-1).z;
					ocean[r][man_c] = 0;
					break;
				}
			}
			// 상어 이동
			move();
		}
		
		System.out.println(answer);
	}
	
	public static void move() {
		List<Shark> newAlive = new ArrayList<>();
		
		for(Shark s : alive) {
			if(ocean[s.r][s.c] == 0) continue;
			
			if(s.d == 1 || s.d == 2) {
				s = moveUpDown(s);
			} else {
				s = moveSide(s);
			}
			newAlive.add(s);
		}
		
		Collections.sort(newAlive, (s1, s2) -> {
			if(s1.r != s2.r) return s1.r - s2.r;
			if(s1.c != s2.c)return s1.c - s2.c; 
			return s2.z-s1.z;
			}
		);
		
		List<Shark> finalAlive = new ArrayList<>();
		for(int i=0; i<newAlive.size(); i++) {
			Shark s = newAlive.get(i);
			finalAlive.add(s);
			while(i+1 < newAlive.size() && newAlive.get(i+1).r == s.r && newAlive.get(i+1).c == s.c) {
				i+=1;
			}
		}
		
		
		alive = finalAlive;
		for(int i=0; i<=R; i++) {
			Arrays.fill(ocean[i], 0);
		}
		for(int i=0; i<alive.size(); i++) {
			Shark s = alive.get(i);
			ocean[s.r][s.c] = i+1;
		}
	}
	
	public static Shark moveUpDown(Shark s) {
		for(int i=0; i<s.s; i++) {
			int nr = s.r + dr[s.d];
			if(nr < 1 || nr > R) {
				s.d = bounceDir(s.d);
				nr = s.r + dr[s.d];
			}
			s.r = nr;
		}
		return s;
	}
	
	public static Shark moveSide(Shark s) {
		for(int i=0; i<s.s; i++) {
			int nc = s.c + dc[s.d];
			if(nc < 1 || nc > C) {
				s.d = bounceDir(s.d);
				nc = s.c + dc[s.d];
			}
			s.c = nc;
		}
		return s;
	}
	
	public static int bounceDir(int dir) {
		if(dir == 1) return 2;
		if(dir == 2) return 1;
		if(dir == 3) return 4;
		return 3;
	}
}