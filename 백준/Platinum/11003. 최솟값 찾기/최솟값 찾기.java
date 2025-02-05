import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, L;
	static Integer[] nums;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Deque<Integer> dq = new ArrayDeque<>();
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		nums = new Integer[N];
		
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// dq의 인덱스 0은 항상 최솟값의 인덱스
		for(int i=0; i<N; i++) {

			// L 범위를 벗어나면 범위 맞을 때까지 덱 첫번째 값 뺴기
			while(!dq.isEmpty() && i-L+1 > dq.peekFirst()) 
				dq.pollFirst();
			
			// 맨 마지막 값과 비교해서 더 큰 값 다 뽑아내기
			// peekLast 써야하는 데 peek 써서 틀렸음..
			while(!dq.isEmpty() && nums[i] <= nums[dq.peekLast()]) 
				dq.pollLast();
			
		
			dq.add(i);
			sb.append(nums[dq.getFirst()].toString() + " ");
		}
		
		System.out.println(sb);
		
	}
}