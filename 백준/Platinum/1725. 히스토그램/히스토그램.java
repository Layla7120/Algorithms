import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int N;
	static int[] bar;
	static int max_sq = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		bar = new int[N+2];
		
		// 그냥 java stack 쓰기...
		ArrayList<Integer> stack = new ArrayList<>();
		int stack_pointer = 0;
		stack.add(0);
		
		for(int i=0; i<N; i++) {
			bar[i+1] = Integer.parseInt(br.readLine());
		}
		
		for(int i=1; i<=N+1; i++) {
			while(!stack.isEmpty() && bar[stack.get(stack_pointer)] > bar[i]) {
				// 잘리는거 다 빼면서 크기 계산
				int idx = stack.remove(stack_pointer--);
				max_sq = Math.max(max_sq, bar[idx] * (i-stack.get(stack_pointer)-1));
			} 
			stack.add(i);
			stack_pointer++;
		}
		
		System.out.println(max_sq);
	}
}