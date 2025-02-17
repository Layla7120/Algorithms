import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, num, idx;
    static int[] arr, d, inputs, ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        inputs = new int[N+1];
        arr = new int[N+1];
        d = new int[N+1]; // dp 각각의 가능한 최대 인덱스
        ans = new int[N+1];

        Arrays.fill(arr, Integer.MAX_VALUE);

        int len = 0;

        for(int i=1; i<=N; i++) {
            num = Integer.parseInt(st.nextToken());
            inputs[i] = num;

            idx = find(num);
            arr[idx] = num;
            d[i] = idx;
            len = Math.max(len, idx);
        }

        sb.append(len).append("\n");

        int max_idx = len;
        for(int i=N; i>0; i--) {
            if(d[i] == max_idx) {
                ans[max_idx--] = inputs[i];
                if(max_idx==-1) break;
            }
        }

        for (int i=1; i<=len; i++) {
            sb.append(ans[i] + " ");
        }


        System.out.println(sb);
    }

    static int find(int num) {
        int left = 1;
        int right = N+1;

        while(left < right) {
            int mid = (left+right) / 2;
            if(arr[mid] < num) {
                left = mid + 1;
            } else
                right = mid;
        }
        return left;
    }
}