import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long K;
	static int total;
	static long[][] dp;
	
	public static void  main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken()); // a 갯수
		M = Integer.parseInt(st.nextToken()); // z 갯수
		K = Long.parseLong(st.nextToken());
		total = N+M;
		
		dp = new long[total+1][total+1];
		
		for(int i=0; i<=total; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
		}
		combination();
		
		if(K > dp[total][N]) {
			System.out.println(-1);
			return;
		}
		while(total > 0) {
			if(N==0) {
				while(total>0) {
					sb.append('z');
					total--;
				}
				break;
			}
			// a 선택
			long pick_a = dp[--total][N-1];
			if(pick_a >= K) {
				sb.append('a');
				N--;
			} else {
				sb.append('z');
				K -= pick_a;
			}
		}
		System.out.println(sb);
		
	}
	
	static void combination() {
		for(int i=2; i<=total; i++) {
			for(int j=1; j<i; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				if(dp[i][j] > 1000000000) {
					dp[i][j] = 1000000001;
					continue;
				}
			}
		}
	}
}