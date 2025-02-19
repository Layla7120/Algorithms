import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, a, b;
	static int[] tree, arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N * 4];
		arr = new int[N+1];

		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1, 0, N-1);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			sb.append(query(1, a-1, b-1, 0, N-1));
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	static int query(int node, int start, int end, int left, int right) {
		if(right < start || end < left) {
			return 0;
		}
		
		if(start <= left && right <= end) {
			return tree[node];
		}
		
		int mid = (left + right) / 2;
		return query(node*2, start, end, left, mid) + 
				query(node*2+1, start, end, mid+1, right);
	}
	
	
	static int init(int node, int left, int right) {
		if(left == right) {
			tree[node] = arr[left];
			return tree[node];
		}
		
		if(tree[node] != 0)
			return tree[node];
		
		int mid = (left + right) / 2;
		int l = init(node*2, left, mid);
		int r = init(node*2+1, mid+1, right);
		
		tree[node] = l + r;
		return tree[node];
	}
}