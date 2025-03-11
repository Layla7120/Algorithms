import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int a, b, c;
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		long answer = 0;
		for(int i=0; i<N; i++) {
			a = arr[i];
			answer += 1; // 주감독 
			
			a -= b; 
			if(a > 0) {
				answer += a/c;
				if(a%c != 0)
					answer += 1;
			}
			
		}
		
		System.out.println(answer);
	}
}