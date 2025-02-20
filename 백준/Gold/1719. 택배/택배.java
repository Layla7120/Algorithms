import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, a, b, c;
	static int[][] arr, ans, edges;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		ans = new int[N+1][N+1];
		edges = new int[N+1][N+1];
		
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				arr[i][j] = 100001;
				ans[i][j] = 2000;
			}
			arr[i][i] = 0;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			arr[a][b] = c;
			arr[b][a] = c;
			ans[a][b] = b;
			ans[b][a] = a;
			edges[a][b] = 1;
			edges[b][a] = 1;
		}
		
		for(int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					if(arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
						int t = k;
						while(i != t) {
							if(ans[i][t] == t) {
								ans[i][j] = t;
								break;
							}
							t = ans[i][t];
						}
					}
				}
			}
		}
		
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if(i==j)
					sb.append("- " );
				else
					sb.append(ans[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
}