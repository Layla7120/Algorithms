import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 자료형 long
public class Main {
	static int N, M, a, b, c;
	static long[][] value;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		value = new long[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				value[i][j] = Integer.MAX_VALUE;
			}
			value[i][i] = 0;
		}
		
		for(int i=1; i<=M; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			value[a][b] = Math.min(value[a][b], c);
		}

		for(int k=1; k <=N; k++) {
			for(int i=1; i <= N; i++) {
				for(int j=1; j<=N; j++) {
					value[i][j] = Math.min(value[i][j], value[i][k] + value[k][j]);
				}
			}
		}
		
		for(int i=1; i <= N; i++) {
			for(int j=1; j<=N; j++) {
				long val = value[i][j];
				if(val == Integer.MAX_VALUE) {
					sb.append(0 + " ");
				} else {
					sb.append(val + " ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
}