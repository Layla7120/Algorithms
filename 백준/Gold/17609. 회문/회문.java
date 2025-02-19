import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static String str;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			str = br.readLine();
			int start = 0;
			int end = str.length()-1;
			int chance = 2;
			
			chance = Math.min(chance, find(start, end, 0));
			
			sb.append(chance + "\n");
		}
		System.out.println(sb);
	}
	
	static int find(int start, int end, int chance) {
		if(start >= end) {
			if(chance > 1)
				chance = 2;
			return chance;
		}
		
		if(str.charAt(start) == str.charAt(end)) {
			return find(start+1, end-1, chance);
		}
		
		int ans = 2;
		if(chance == 0) {
			if(str.charAt(start+1) == str.charAt(end)) {
				ans = Math.min(ans, find(start+1, end, chance+1));
			}
			if(str.charAt(start) == str.charAt(end-1)) {
				ans = Math.min(ans, find(start, end-1, chance+1));
			}
		}
		return ans;
	}
}