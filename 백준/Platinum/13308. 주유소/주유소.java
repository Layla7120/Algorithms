import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 자료형 long
public class Main {
	static int N, M, a, b, c, cur, next, minPrice;
	static int[] price;
	static long total, nextW;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ArrayList<ArrayList<int[]>> edges = new ArrayList<>();
		
		N = Integer.parseInt(st.nextToken()); // 도시의 수
		M = Integer.parseInt(st.nextToken()); // 도로의 수
		price = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<=N; i++) {
			edges.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			edges.get(a).add(new int[] {b, c});
			edges.get(b).add(new int[] {a, c});
		}
		

		long[][] costs = new long[N+1][2501];
		for(int i=1; i<=N; i++) {
			for(int j=0; j<=2500; j++) {
				costs[i][j] = Long.MAX_VALUE;
			}
		}
		
		// 이거 덕분에 cur == N일 때 바로 정답 추출 가
		PriorityQueue<long[]> q = new PriorityQueue<>((o1, o2) -> {
			return (int)(o1[1] - o2[1]);
		});
		
		//cost[node, 최소비용값] = 누적 비용값
		
		q.add(new long[] {1, 0, price[1]}); // node, 누적 비용값, 최소비용값
		while(!q.isEmpty()) {
			cur = (int) q.peek()[0];
			total = q.peek()[1];
			minPrice = (int) q.peek()[2];
			
			q.poll();
			
			if(cur == N) {
				System.out.println(total);
				break;
			}
			
			for(int i=0; i<edges.get(cur).size(); i++) {
				next = (int) edges.get(cur).get(i)[0];
				nextW = edges.get(cur).get(i)[1];
				
				long newCost = total + minPrice*nextW;
				if(costs[next][minPrice] > newCost) {
					costs[next][minPrice] = newCost;
					q.add(new long[] {next, newCost, Math.min(minPrice, price[next])});
				}
			}
		}
		
	}
}