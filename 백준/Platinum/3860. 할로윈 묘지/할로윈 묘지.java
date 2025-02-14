import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static int W, H, G, X1, Y1, X2, Y2, E, T;
	static int INF = Integer.MAX_VALUE;
	static int[][] board;
	static BufferedReader br;
	static StringTokenizer st;
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	
	
	public static void main(String args[]) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken()); // 너비
			H = Integer.parseInt(st.nextToken()); // 높이

			if(W == 0 && H == 0)
				break;

			ArrayList<ArrayList<int[]>> edges = new ArrayList<>();
			int[] dist = new int[W*H];	
			
			for(int i=0; i<W*H; i++) {
				edges.add(new ArrayList<>());
				dist[i] = INF;
			}
			
			G = Integer.parseInt(br.readLine()); // 묘비의 개수
			
			// 묘비 표시
			board = new int[H][W];
			for(int i=0; i<G; i++) {
				st = new StringTokenizer(br.readLine());
				X1 = Integer.parseInt(st.nextToken());
				Y1 = Integer.parseInt(st.nextToken());
				
				board[Y1][X1] = 1;
			}
			
			// 그래프 만들기
			for(int x=0; x<W; x++) {
				for(int y=0; y<H; y++) {
					if(board[y][x] == 1)
						continue;
					
					 if (y == (H-1) && x == (W-1)) 
	                    continue;
					
					int node = y*W + x;
					
					for(int i=0; i<2; i++){
						int nx = x + dx[i];
						int ny = y + dy[i];
						
						if(nx >= 0 && ny >= 0 && nx < W && ny < H) {
							if(board[ny][nx] != 1) {
								int next = ny*W + nx;
								edges.get(node).add(new int[] {next, 1});
								edges.get(next).add(new int[] {node, 1});
							}
						}
					}
				}
			}
			
			
			// 귀신 구멍 - 간선 연결
			E = Integer.parseInt(br.readLine()); // 구멍의 수
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				X1 = Integer.parseInt(st.nextToken());
				Y1 = Integer.parseInt(st.nextToken());
				X2 = Integer.parseInt(st.nextToken());
				Y2 = Integer.parseInt(st.nextToken());
				T = Integer.parseInt(st.nextToken());
				
				int node = Y1*W + X1;
				int next = Y2*W + X2;
				
				// 귀신 구멍은 무조건 타야함
				edges.get(node).clear();
				edges.get(node).add(new int[] {next, T});
			}
			
			
			// 모든 간선에 대해서 정점의 수 - 1만큼 업데이트
			int next, time;
			dist[0] = 0;
			
			for(int c=0; c<W*H-G; c++) {
				// 탈출 노드 제외
				for(int n=0; n<W*H-1; n++) {
					for(int i=0; i<edges.get(n).size(); i++) {
						next = edges.get(n).get(i)[0];
						time = edges.get(n).get(i)[1];
						
						// dist[n] == INF 이면 아직 닿지 못한 곳
						if(dist[n] == INF)
							continue;
						
						if(dist[next] > dist[n]+time) {
							dist[next] = dist[n]+time;
						}
					}
				}
			}
			
			boolean flag = false;
			for(int n=0; n<W*H-1; n++) {
				for(int i=0; i<edges.get(n).size(); i++) {
					next = edges.get(n).get(i)[0];
					time = edges.get(n).get(i)[1];
					
					if(dist[n] == INF) {
						continue;
					}
					
					if(dist[next] > dist[n]+time) {
						flag = true;
						break;
					}
				}
			}
			
			if(flag) {
				sb.append("Never\n");
			} else {
				if(dist[W*H-1] == INF) {
					sb.append("Impossible\n");
				} else {
					sb.append(dist[W*H-1] + "\n");
				}
			}
		}

		System.out.println(sb);
	}
}