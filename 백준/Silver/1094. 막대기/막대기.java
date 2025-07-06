import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = 0;

        while(N != 1 && N != 0){
            if(N % 2 == 1) answer++;
            N = N / 2;
        }

        if(N == 1) answer++;

        System.out.println(answer);
    }
}
