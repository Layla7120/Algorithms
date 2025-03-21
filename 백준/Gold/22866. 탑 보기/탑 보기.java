import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] answer, idx;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N+1];
		answer = new int[N+1];
		idx = new int[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			idx[i] = Integer.MAX_VALUE;
		}
		
		Stack<Integer> leftStack = new Stack<>();
		Stack<Integer> rightStack = new Stack<>();
		
		for(int i=1; i<=N; i++) {
			while(leftStack.size() > 0) {
				if(arr[i] >= arr[leftStack.peek()]) 
					leftStack.pop();
				else
					break;
			}
			
			answer[i] += leftStack.size();
			if(leftStack.size() > 0) {
				if(Math.abs(idx[i] - i) > Math.abs(leftStack.peek() - i))
					idx[i] = leftStack.peek();
			}
			
			leftStack.add(i);
		}
		
		for(int i=N; i>=1; i--) {
			while(rightStack.size() > 0) {
				if(arr[i] >= arr[rightStack.peek()])
					rightStack.pop();
				else
					break;
			}
			
			answer[i] += rightStack.size();
			if(rightStack.size() > 0)
				if(Math.abs(idx[i] - i) > Math.abs(rightStack.peek() - i))
					idx[i] = rightStack.peek();
				
			
			rightStack.add(i);
		}
		
		for(int i=1; i<=N; i++) {
			if(answer[i] == 0)
				System.out.println(answer[i]);
			else
				System.out.println(answer[i] + " " + idx[i]);
		}
	}
}