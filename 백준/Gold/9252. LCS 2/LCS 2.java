import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int max;
	static String[] arrA, arrB;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		arrA = br.readLine().split("");
		arrB = br.readLine().split("");
		
		dp = new int[arrA.length+1][arrB.length+1];
		for(int i=0; i<arrA.length; i++) {
			for(int j=0; j<arrB.length; j++) {
				if(arrA[i].equals(arrB[j])) {
					dp[i+1][j+1] = dp[i][j] + 1;
					continue;
				}
				
				dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
			}
		}
		
		int aIdx=arrA.length-1;
		int bIdx=arrB.length-1;
		String ans = "";
		
		while(true) {
			if(aIdx == -1 || bIdx == -1) {
				break;
			}
			
			if(arrA[aIdx].equals(arrB[bIdx])) {
				ans += arrA[aIdx];
				aIdx--;
				bIdx--;
			} else {
				if(dp[aIdx][bIdx+1] == dp[aIdx+1][bIdx+1])
					aIdx--;
				else if(dp[aIdx+1][bIdx] == dp[aIdx+1][bIdx+1])
					bIdx--;
			}
		}
		
		sb.append(ans.length()).append("\n");
		
		for(int i=ans.length()-1; i>=0; i--) {
			sb.append(ans.charAt(i));
		}
		
		System.out.println(sb);
	}
}