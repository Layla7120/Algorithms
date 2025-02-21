import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k, a, b, c;
	static int cur, curW, next, nextW;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ArrayList<ArrayList<int[]>> edges = new ArrayList<>();
		ArrayList<PriorityQueue<Integer>> costs = new ArrayList<>();
		
		n = Integer.parseInt(st.nextToken()); // 도시의 개수
		m = Integer.parseInt(st.nextToken()); // 도시 간 존재하는 도로의 수
		k = Integer.parseInt(st.nextToken()); // k 번째 최단경로
		
		for(int i=0; i<=n; i++) {
			edges.add(new ArrayList<>());
			costs.add(new PriorityQueue<>((o1, o2) -> {
				return o2 - o1;
			}));
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			edges.get(a).add(new int[] {b, c});
		}
		
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
			return o1[1] - o2[1];
		});
		
		costs.get(1).add(0);
		
		
		q.add(new int[] {1, 0});
		while(!q.isEmpty()) {
			cur = q.peek()[0];
			curW = q.peek()[1];
			q.poll();
			for(int i=0; i<edges.get(cur).size(); i++) {
				next = edges.get(cur).get(i)[0];
				nextW = edges.get(cur).get(i)[1];
				
				
				// 우선순위큐에 자리가 남으면 그냥 넣고
				// 아니면 제일 비싼 값 가져와서 비교후 빼고 넣기
				if(costs.get(next).size() < k) {
					costs.get(next).add(curW + nextW);
					q.add(new int[] {next, curW + nextW});
				} else {
					int w = costs.get(next).peek();
					if(costs.get(next).size() == k && w > curW + nextW) {
						costs.get(next).poll();
						costs.get(next).add(curW + nextW);
						q.add(new int[] {next, curW + nextW});
					}
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			if(costs.get(i).size() != k)
				System.out.println(-1);
			else
				System.out.println(costs.get(i).peek());
		}
	}
}