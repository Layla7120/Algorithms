import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int parent[][], depth[], visited[];
	static ArrayList<ArrayList<Integer>> edges;
	
	public static void  main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine()); // 노드의 개수
		K = 30;
		parent = new int[K][N+1];
		depth = new int[N+1];
		edges = new ArrayList<>();
		
		for (int i=0; i<=N; i++) {
            edges.add(new ArrayList<>());
        }
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			edges.get(n).add(m);
			edges.get(m).add(n);
		}
		
		// 모든 노드의 깊이 계산 + 부모 계산
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		depth[1] = 1;
		int cur, next;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int i=0; i<edges.get(cur).size(); i++) {
				next = edges.get(cur).get(i);
				if(depth[next] == 0) {
					depth[next] = depth[cur]+1;
					parent[0][next] = cur;
					q.add(next);
				}
			}
		}
		
		// DP 테이블
		for(int k=1; k<K; k++) {
			for(int i=1; i<=N; i++) {
				parent[k][i] = parent[k-1][parent[k-1][i]];
			}
		}
		
		
		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b) + "\n");
		}
		
		System.out.println(sb);
	}
	
	static int lca(int a, int b) {
		if (depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		for(int k=K-1; k>=0; k--) {
			if(depth[b] - depth[a] >= (1 << k)) {
				b = parent[k][b];
			}
		}
		if(a==b) {
			return a;
		}
		
		for(int k=K-1; k>=0; k--) {
			if(parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
		return parent[0][a];
	}
}