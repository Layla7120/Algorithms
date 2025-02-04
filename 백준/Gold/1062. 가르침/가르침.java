import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static String[] words;
	static int[] visited;
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N < 50,  0<=K<= 26
		
		N = Integer.parseInt(st.nextToken());
		// 이미 a, n, t, i, c가 있으니까 5을 빼줘야함
		K = Integer.parseInt(st.nextToken()) - 5;
		
		words = new String[N];
		visited = new int[26];
		
		//a, n, t, i, c
		visited['a' - 'a'] = 1;
		visited['n' - 'a'] = 1;
		visited['t' - 'a'] = 1;
		visited['i' - 'a'] = 1;
		visited['c' - 'a'] = 1;
		
		for(int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		
		if(K < 0) {
			System.out.println(0);
			return;
		}
		
		// 백트래킹
		answer = 0;
		choose(0, 0);
		System.out.println(answer);
		
	}
	
	// 모든 알파펫을 돌기
	private static void choose(int cnt, int idx) {
		if (cnt == K) {
			int total = 0;
			// 몇개 단어 읽을 수 있는 지 보기
			for (int w = 0; w < words.length; w++) {
				String word = words[w];
				boolean flag = true;
				for(int j = 4; j < word.length() - 4; j++) {
					if (visited[word.charAt(j) - 'a'] == 0) {
						flag = false;
						break;
					}
				}
				if(flag)
					total++;
			}
			answer = Math.max(answer, total);
			return;
		}
		
		for (int i = idx; i < 26; i++) {
			if (visited[i] == 0) {
				visited[i] = 1;
				choose(cnt + 1, i + 1);
				visited[i] = 0;
			}
		}
	}
}