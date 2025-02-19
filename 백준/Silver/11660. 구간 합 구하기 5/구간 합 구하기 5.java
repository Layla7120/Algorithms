import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, x1, x2, y1, y2, total;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 구간합 계산하기
		
		for(int i=2; i<=N; i++) {
			arr[1][i] += arr[1][i-1];
			arr[i][1] += arr[i-1][1];
		}
		
		for(int i=2; i<=N; i++) {
			for(int j=2; j<=N; j++) 
				arr[i][j] = arr[i-1][j] + arr[i][j-1] + arr[i][j] - arr[i-1][j-1];
		}
		
		for(int m=1; m<=M; m++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			int total = arr[x2][y2] - arr[x2][y1-1] - arr[x1-1][y2] + arr[x1-1][y1-1];
			
			sb.append(total).append("\n");
		}
		
		System.out.println(sb);
	}
}