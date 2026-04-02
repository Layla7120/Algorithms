import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] board;
  static int MaxNum = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    board = new int[N][N];

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      for (int j = 0; j < N; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // DFS

    DFS(0, board);

    System.out.println(MaxNum);
  }

  public static void DFS(int count, int[][] board) {
    if (count == 5) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          MaxNum = Math.max(MaxNum, board[i][j]);
        }
      }
      return;
    }

    for (int i = 0; i < 4; i++) {
      int[][] copiedBoard = new int[N][N];
      for (int j = 0; j < N; j++) {
        copiedBoard[j] = board[j].clone();
      }

      pushUp(copiedBoard);
      DFS(count + 1, copiedBoard);
      board = turn90(board);
    }
  }

  // 90 도 돌리기 함수
  public static int[][] turn90(int[][] board) {
    int[][] new_board = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        new_board[j][N - i - 1] = board[i][j];
      }
    }

    return new_board;
  }

  // UP 함수
  public static void pushUp(int[][] board) {
    for (int col = 0; col < N; col++) {
      ArrayDeque<Integer> q = new ArrayDeque<>();
      for (int row = 0; row < N; row++) {
        if (board[row][col] != 0)
          q.add(board[row][col]);
        board[row][col] = 0;
      }

      int insertRow = 0;
      int prev = -1;

      while (!q.isEmpty()) {
        int num = q.pollFirst();

        if (prev == -1) {
          prev = num;
          board[insertRow][col] = num;
        } else if (num == prev) {
          board[insertRow++][col] = prev * 2;
          prev = -1;
        } else {
          prev = num;
          board[++insertRow][col] = num;
        }
      }
    }
  }
}