import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int[] result = new int[N];

        for (int i = 1; i <= N; i++) {
            result[i - 1] = bfs(N, graph, i);
        }

        int min_val = Arrays.stream(result).min().getAsInt();
        for (int i = 0; i < N; i++) {
            if (result[i] == min_val) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    private static int bfs(int N, List<List<Integer>> graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[N + 1];
        Arrays.fill(distance, -1);
        distance[start] = 0;

        queue.add(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                if (distance[next] == -1) { // 방문하지 않은 경우만 처리
                    distance[next] = distance[cur] + 1;
                    queue.add(next);
                }
            }
        }

        return Arrays.stream(distance).filter(d -> d != -1).sum();
    }
}
