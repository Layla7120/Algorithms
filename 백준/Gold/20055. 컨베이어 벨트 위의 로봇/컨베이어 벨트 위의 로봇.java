import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K, count = 1, zeros;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[2*N];
        int[] status = new int[2*N];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=2*N; i++) {
        	arr[i % (2*N)] = Integer.parseInt(st.nextToken());
        }
        
        int start = 1, end = N;
        
        while(true){
        	start = move(start);
        	end = move(end);
        	
        	if(status[end] == 1) {
        		status[end] = 0;
        	}
        	for(int i=(end-1+2*N)% (2*N); i!=(start+2*N) %(2*N); i = (i-1+2*N)% (2*N)) {
        		int next = (i + 1) % (2 * N);
        		if (status[i] == 1 && status[next] == 0 && arr[next] > 0) {
        		    status[i] = 0;
        		    status[next] = 1;
        		    arr[next]--;
        		}
        	}
        	if(status[end] == 1) {
        		status[end] = 0;
        	}
        	
        	if(arr[start]!=0 && status[start] == 0)  {
        		status[start] = 1;
        		arr[start]--;
        	}
        	
        	zeros = 0;
        	for (int i = 0; i < 2 * N; i++) {
        		if (arr[i] == 0) zeros++;
        	}
        	
        	if(zeros >= K) {
        		break;
        	}

        	count++;
        }
        
        System.out.println(count);
    }
    
    private static int move(int n) {
    	return (n-1 + 2*N)%(2*N);
    }

}