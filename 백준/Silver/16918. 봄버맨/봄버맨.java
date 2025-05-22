import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, N;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        String values;

        // input 받기
        for(int i=0; i<R; i++){
            values = br.readLine();
            for(int j=0; j<C; j++) {
                if(values.charAt(j) == '.'){
                    board[i][j] = -1;
                } else {
                    board[i][j] = 0;
                }
            }
        }


        for(int t=1; t<=N; t++){
            if(t == 1){
                continue;
            }

            if(t % 2 == 0){
                installBomb(t);
            } else {
                explodeBomb(t);
            }
        }

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(board[i][j] == -1){
                    sb.append(".");
                } else {
                    sb.append("O");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void installBomb(int time){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(board[i][j] == -1){
                    board[i][j] = time;
                }
            }
        }
    }

    private static void explodeBomb(int time){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if((time - board[i][j]) == 3){
                    removeAdjacentBombs(i, j);
                    board[i][j] = -1;
                }
            }
        }
    }

    private static void removeAdjacentBombs(int i, int j){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for(int d=-0; d<4; d++){
            int new_i = i + dx[d];
            int new_j = j + dy[d];

            if(new_i >= 0 && new_i < R && new_j >= 0 && new_j < C){
                if(board[new_i][new_j] != board[i][j]){
                    board[new_i][new_j] = -1;
                }

            }
        }
    }
}

