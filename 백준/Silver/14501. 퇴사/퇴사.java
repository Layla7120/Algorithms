import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, answer;
	static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][2];
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        choose(0, 0);
        System.out.println(answer);
    }
    
    private static void choose(int n, int val) {
    	if(n > N) {
    		answer = Math.max(answer,  val);
    		return;
    	}
    	
		if(n+1 <= N && n+arr[n+1][0] <= N) {
			choose(n+arr[n+1][0], val + arr[n+1][1]);
		}
		choose(n+1, val);
    }

}