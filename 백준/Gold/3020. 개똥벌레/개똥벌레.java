import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, H;
	static ArrayList<Integer> top;
	static ArrayList<Integer> bottom;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N, H
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// TOP. BOTTOM
		top = new ArrayList<>();
		bottom = new ArrayList<>();
		
		
		for (int i=0; i<N; i++) {
			if(i % 2 == 1) {
				top.add(Integer.parseInt(br.readLine()));
				continue;
			}
			bottom.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(top);
		Collections.sort(bottom);
		
		int barrier_count = Integer.MAX_VALUE;
		int row_count = 0;
		
		for(int r = 1; r <= H; r++) {
			//몇 개 부숴야하는지
			int total_count = 0;
			
			total_count = top.size() - lowerBound(top, r);
			total_count += bottom.size() - lowerBound(bottom, H - r + 1);
			
			// 최솟값이면 값 업데이트
			if (total_count < barrier_count) {
				barrier_count = total_count;
				row_count = 1;
			}
			else if (total_count == barrier_count)
				row_count++;
			
		}
		System.out.printf("%d %d", barrier_count, row_count);
	}
	
	private static int lowerBound(ArrayList<Integer> arraylist, Integer target) {
		int left = 0;
		int right = arraylist.size();
		int mid;
		
		while (left < right) {
			mid = (left + right) / 2;
			if(arraylist.get(mid) < target) {
				left = mid + 1;
				continue;
			}
			right = mid;
		}
		return right;
	}
}