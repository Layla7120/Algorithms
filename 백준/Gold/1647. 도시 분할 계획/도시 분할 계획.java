import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 자료형 long
public class Main {
	static int N, M, a, b, c;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return o1[2] - o2[2];
		});
		
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			pq.add(new int[] {a, b, c});
		}
		
		int count = 0;
		int total = 0;
		int maxVal = 0; // 맨 마지막에 없애줄 
		
		while(!pq.isEmpty()) {
			if(count == N-1) {
				break;
			}
			int[] temp = pq.poll();
			a = temp[0];
			b = temp[1];
			c = temp[2];
			
			if(find(a) != find(b)) {
				maxVal = Math.max(maxVal, c);
				
				union(a, b);
				
				total += c;
				count++;
			}
		}
		System.out.println(total - maxVal);
	}
	
	static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if(A != B) {
			parent[B] = A;
		}
	}
	
	static int find(int a) {
		if(a == parent[a])
			return a;
		
		// 경로 압축
		parent[a] = find(parent[a]);
		return parent[a];
	}
}