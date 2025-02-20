import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, K, M; 
	static int[] depth, arr;
	static int[][] parent;
	static ArrayList<ArrayList<Integer>> edges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken()); // 학생 수
		K = Integer.parseInt(st.nextToken()); // 동영상 개수
		M = Integer.parseInt(st.nextToken()); // 남은 수업시간
		
		arr = new int[N+1];
		depth = new int[N+1];
		parent = new int[31][K+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=K; i++) {
			parent[0][i] = Integer.parseInt(st.nextToken());
		}
		
		for(int k=1; k<31; k++) {
			for(int i=1; i<=K; i++) {
				parent[k][i] = parent[k-1][parent[k-1][i]];
			}
		}
		
		
		for(int i=1; i<=N; i++) {
			int num = arr[i];
			int m = M-1;
			for(int k=30; k>=0; k--) {
				if(m >= (1<<k)) {
					m -= (1<<k);
					num = parent[k][num];
				}
			}
			sb.append(num).append(" ");
		}
		
		
		System.out.println(sb);
	}
}