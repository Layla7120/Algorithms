import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int total = 1;
		
		for(int i=1; i<=N; i++) {
			total *= i;
		}
		
		System.out.println(total);
	}
}