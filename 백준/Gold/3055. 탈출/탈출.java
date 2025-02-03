import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] visited;
    static int min_count = Integer.MAX_VALUE;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new int[R][C];

        Queue<int[]> hedgehogQueue = new LinkedList<>();
        Queue<int[]> waterQueue = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') { // 고슴도치 위치
                    hedgehogQueue.offer(new int[]{j, i, 0});
                    visited[i][j] = 1;
                } else if (map[i][j] == '*') { // 물 위치
                    waterQueue.offer(new int[]{j, i});
                }
            }
        }

        bfs(hedgehogQueue, waterQueue);
        System.out.println(min_count == Integer.MAX_VALUE ? "KAKTUS" : min_count);
    }

    private static void bfs(Queue<int[]> hedgehogQueue, Queue<int[]> waterQueue) {
        while (!hedgehogQueue.isEmpty()) {
            flood(waterQueue);
            int size = hedgehogQueue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = hedgehogQueue.poll();
                int x = cur[0], y = cur[1], cnt = cur[2];

                if (map[y][x] == 'D') {
                    min_count = cnt;
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (ny < 0 || nx < 0 || ny >= R || nx >= C)
                        continue;

                    if (visited[ny][nx] == 0 && map[ny][nx] != '*' && map[ny][nx] != 'X') {
                        visited[ny][nx] = 1;
                        hedgehogQueue.offer(new int[]{nx, ny, cnt + 1});
                    }
                }
            }
        }
    }

    private static void flood(Queue<int[]> waterQueue) {
        int size = waterQueue.size();
        for (int i = 0; i < size; i++) {
            int[] cur = waterQueue.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C)
                    continue;

                if (map[ny][nx] == '.') {
                    map[ny][nx] = '*';
                    waterQueue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}