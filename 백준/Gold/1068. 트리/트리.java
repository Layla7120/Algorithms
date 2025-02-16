import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int N, M, root, answer=0;
    static ArrayList<ArrayList<Integer>> child;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        child = new ArrayList<>();
        for(int i = 0; i <=N; i++){
            child.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent_idx = Integer.parseInt(st.nextToken());
            if(parent_idx < 0){
                root = i;
                continue;
            }
            child.get(parent_idx).add(i);
        }

        M = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int n = q.poll();

            if(n != M) {
                int idx = child.get(n).indexOf(M);
                if(idx != -1){
                    child.get(n).remove(idx);
                }
                if (child.get(n).isEmpty())
                    answer++;
                else {
                    q.addAll(child.get(n));
                }
            }
        }

        System.out.println(answer);
    }

}