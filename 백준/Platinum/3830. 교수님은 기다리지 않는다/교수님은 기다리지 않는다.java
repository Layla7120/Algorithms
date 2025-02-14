import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, a, b, w;
	static int[] parent, d;
	
	public static void  main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 샘플의 개수
			M = Integer.parseInt(st.nextToken()); // 한 일의 수
			
			if(N == 0 && M == 0) {
				System.out.println(sb);
				return;
			}
			
			parent = new int[N+1];
			for(int i=1; i<=N; i++) {
				parent[i] = i;
			}
			
			d = new int[N+1];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				String flag = st.nextToken();
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if(flag.charAt(0) == '!')  {
					w = Integer.parseInt(st.nextToken());
					union(a, b, w);
				} else{
					sb.append(getDiff(a, b) + "\n");
				}
			}
			
		}
	}
	
	static String getDiff(int a, int b) {
		if(find(a) == find(b)) {
			return Integer.toString(d[b] - d[a]);
		} 
		return "UNKNOWN";
	}
	
	static void union(int a, int b, int w) {
		int A = find(a);
		int B = find(b);
		
		if(A != B) {
			// 무게 update 처리
			// B 가 아니라 b로 했음
			d[B] = d[a]-d[b]+w;
			parent[B] = A;
		} 
	}
	
	static int find(int a) {
		if(a == parent[a]) {
			return a;
		}
		
		int p = find(parent[a]);
		d[a] += d[parent[a]];
		parent[a] = p;
		
		return parent[a];
	}
}