import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int max_num = Integer.MIN_VALUE, N;
    static int min_num = Integer.MAX_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> ops = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            ops.add(Integer.parseInt(st.nextToken()));
        }

        recursion(1, arr[0], ops);

        System.out.println(max_num);
        System.out.println(min_num);
    }

    private static void recursion(int idx, int total, ArrayList<Integer> ops){
        if(idx == N){
            max_num = Math.max(max_num, total);
            min_num = Math.min(min_num, total);
            return;
        }

        for(int i = 0; i < 4; i++){
            if(idx == N-1 && ops.get(i) == 1){
                recursion(idx + 1, calculate(total, arr[idx], i), ops);
            }
            else if(ops.get(i) >= 1) {
                ops.set(i, ops.get(i) - 1);
                recursion(idx + 1, calculate(total, arr[idx], i), ops);
                ops.set(i, ops.get(i) + 1);
            }
        }
    }

    private static int calculate(int a, int b, int op){
        switch(op){
            case 0:
                return a+b;
            case 1:
                return a-b;
            case 2:
                return a*b;
            case 3:
                if(a < 0){
                    return -(-a/b);
                }
                return a/b;
        }
        return 0;
    }
}