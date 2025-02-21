import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int N, K, ans;
	static int[] arr, nums;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		nums = new int[100001];
		ans = 1; 
		
		int left = 0;
		int right = 0;
		while(left<=right && right < N) {
			nums[arr[right]] += 1;
			if(nums[arr[right]] > K) {
				ans = Math.max(right-left, ans);
				while(nums[arr[right]] > K) {
					nums[arr[left]] -= 1;
					left++;
				}
			}
			right++;
		}
		
		if(left != right) {
			ans = Math.max(right-left, ans);
		}
		System.out.println(ans);
	}
}