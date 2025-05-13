import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int A = Integer.parseInt(br.readLine());
        String B = br.readLine();
        int B_int = Integer.parseInt(B);

        for(int i=2; i>=0; i--){
            int n = Integer.parseInt(String.valueOf(B.charAt(i)));
            sb.append(A*n).append("\n");
        }

        sb.append(A*B_int);
        System.out.println(sb);
    }
}
