import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, min_kudo;
	static int[] kudos;
	public static void  main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> lst = new ArrayList<>();
	
		N = Integer.parseInt(br.readLine()); // 사진
		M = Integer.parseInt(br.readLine()); // 추천 횟수 
		kudos = new int [101];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		for(int i=0; i<M; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			kudos[n]++;
			if(kudos[n] == 1) {
				if(lst.size() == N) {
					PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) ->  {
						return kudos[x] - kudos[y];
					});
					pq.addAll(lst);
					int removeIdx = pq.poll();
					kudos[removeIdx] = 0;
					lst.remove(Integer.valueOf(removeIdx));
				}
				lst.add(n);
			}
		}
		
		Collections.sort(lst);
		
		for(int i=0; i<lst.size(); i++) {
			sb.append(lst.get(i) + " ");
		}
		
		System.out.println(sb);
	}

}