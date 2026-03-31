import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.io.BufferedReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    LinkedList<Integer> list = new LinkedList<>();
    st = new StringTokenizer(br.readLine());

    for (int i = 1; i <= N; i++) {
      list.add(i);
    }

    int answer = 0;

    for (int i = 0; i < M; i++) {
      int target = Integer.parseInt(st.nextToken());

      int targetIdx = list.indexOf(target);
      int halfIdx = list.size() / 2;

      if (targetIdx <= halfIdx) {
        for (int j = 0; j < targetIdx; j++) {
          list.addLast(list.pollFirst());
          answer++;
        }
      } else {
        for (int j = 0; j < list.size() - targetIdx; j++) {
          list.addFirst(list.pollLast());
          answer++;
        }
      }
      list.pollFirst();
    }

    System.out.println(answer);
  }

}