import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int T, M, count; //테스트 케이스 수
	static int mid;
	static int[] nums;
	static ArrayList<Integer> ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		
		for (int t=0; t<T; t++) {
			M = Integer.parseInt(br.readLine());
			nums = new int[M];
			ans = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());;
			
			PriorityQueue<Integer> maxs = new PriorityQueue<>();
			PriorityQueue<Integer> mins = new PriorityQueue<>((o1, o2) -> {
				return Integer.compare(o2, o1);
			});
			
			nums[0] = Integer.parseInt(st.nextToken());
			mid = nums[0];
			ans.add(mid);
			for(int i=1; i<M; i++) {
				if(!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				nums[i] = Integer.parseInt(st.nextToken());

				if(nums[i] >= mid) {
					maxs.add(nums[i]);
				} else {
					mins.add(nums[i]);
				}
				
				if ((i+1) % 2 == 1) {
					while(maxs.size() != mins.size()) {
						if(maxs.size() > mins.size()) {
							mins.add(mid);
							mid = maxs.poll();
						}
						else if(maxs.size() < mins.size()) {
							maxs.add(mid);
							mid = mins.poll();
						}
					}

					ans.add(mid);
					continue;
				}
			}
			
			sb.append(ans.size() + "\n");
			for(int i=0; i<ans.size(); i++) {
				if(i%10 == 0 && i!=0) {
					sb.append("\n");
				}
				sb.append(ans.get(i) + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}