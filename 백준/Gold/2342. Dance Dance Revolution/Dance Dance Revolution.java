import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new int[100001];
		
		int num, idx=0;
		while(true) {
			num = Integer.parseInt(st.nextToken());
			if(num == 0)
				break;
			arr[++idx] = num;
		}
		
		dp = new int[2][5][5];
		
		// 0 이전, 1 현재
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				dp[0][i][j] = Integer.MAX_VALUE;
				dp[1][i][j] = Integer.MAX_VALUE;
			}
		}
		
		dp[0][0][0] = 0;
		int next = 0;
		
		// 들어온 숫자를 순회
		for(int k=1; k<=idx; k++) {
			next = arr[k];
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					// 불가능한 상황이면 패스
					if(dp[0][i][j] == Integer.MAX_VALUE)
						continue;
					
					dp[1][i][next] = Math.min(dp[1][i][next], dp[0][i][j] + cost(j, next));
					dp[1][next][j] = Math.min(dp[1][next][j], dp[0][i][j] + cost(i, next));
				}
			}
			
			// 값 복사하고 현재값 초기화
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					dp[0][i][j] = dp[1][i][j];
					dp[1][i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		// 마지막까지 왔을 때의 최적값들만 업데이트 되어있음
		int ans = Integer.MAX_VALUE;
		for(int i=0; i<5; i++) {
			ans = Math.min(ans, dp[0][i][next]);
			ans = Math.min(ans, dp[0][next][i]);
		}
		
		System.out.println(ans);
	}
	
	static int cost(int a, int b) {
		if(a == b)
			return 1;
		
		if(a == 0 || b == 0) {
			return 2;
		}
		
		if(Math.abs(a-b)==2) {
			return 4;
		}
		
		return 3;
	}
}