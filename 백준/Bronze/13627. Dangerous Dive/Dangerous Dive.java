import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // went
        int R = Integer.parseInt(st.nextToken()); // returned

        int[] repo = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<R; i++){
            int n = Integer.parseInt(st.nextToken());
            repo[n] = 1;
        }

        for(int i=1; i<=N; i++){
            if(repo[i] == 0){
                sb.append(i).append(" ");
            }
        }

        if(sb.length() == 0)
            sb.append('*');

        System.out.println(sb);
    }

}