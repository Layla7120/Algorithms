import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static char[][] board;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class State {
        int x, y;
        int dir;
        int moveCount;

        State(int x, int y, int dir, int moveCount) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.moveCount = moveCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            board[i] = st.nextToken().toCharArray();
        }

        List<int[]> B = new ArrayList<>();
        List<int[]> E = new ArrayList<>();

        for ( int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] == 'B') B.add(new int[]{i, j});
                if(board[i][j] == 'E') E.add(new int[]{i, j});
            }
        }

        int startX = B.get(1)[0];
        int startY = B.get(1)[1];
        int startDir = (B.get(0)[0] == B.get(1)[0]) ?  0 : 1;

        int endX = E.get(1)[0];
        int endY = E.get(1)[1];
        int endDir = (E.get(0)[0] == E.get(1)[0]) ? 0 : 1;

        visited = new boolean[N][N][2];
        int result = bfs(startX, startY, startDir, endX, endY, endDir);
        System.out.println(result);

    }

    static int bfs(int sx, int sy, int sd, int ex, int ey, int ed) {
        Queue<State> q = new LinkedList<>();
        q.offer(new State(sx, sy, sd, 0));
        visited[sx][sy][ed] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.x == ex && cur.y == ey && cur.dir == ed) return cur.moveCount;

            for(int d=0; d<4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if(canMove(nx, ny, cur.dir) && !visited[nx][ny][cur.dir]){
                    visited[nx][ny][cur.dir] = true;
                    q.offer(new State(nx, ny, cur.dir, cur.moveCount+1));
                }
            }

            if(canRotate(cur.x, cur.y) && !visited[cur.x][cur.y][1-cur.dir]){
                visited[cur.x][cur.y][1-cur.dir] = true;
                q.offer(new State(cur.x, cur.y, 1- cur.dir, cur.moveCount+1));
            }
        }
        return 0;
    }

    static boolean canMove(int x, int y, int dir) {
        if(!inRange(x, y)) return false;

        try{
            if(dir == 0){
                return board[x][y-1] != '1' && board[x][y] != '1'&& board[x][y+1] != '1';
            } else {
                return board[x-1][y] != '1' && board[x][y] != '1' && board[x+1][y] != '1';
            }
        } catch (Exception e) {
            return false;
        }
    }

    static boolean canRotate(int x, int y) {
        for(int dx = -1; dx <= 1; dx++){
            for(int dy = -1; dy <= 1; dy++){
                int nx = x + dx, ny = y + dy;
                if(!inRange(nx, ny) || board[nx][ny] == '1') return false;
            }
        }
        return true;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
