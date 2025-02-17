import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, nx, ny;
    static int[][] arr;
    static int[][][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[R+2][C+2];
        dp = new int[R+2][C+2][2];

        int min, ans = 0;
        String line;

        for(int y=1; y<=R; y++) {
        	line = br.readLine();
        	
            for (int x=1; x<=C; x++) {
            	arr[y][x] = line.charAt(x-1) - '0';
            	 
                if(arr[y][x] == 1) {
                    dp[y][x][0] = dp[y-1][x-1][0] + 1;
                    dp[y][x][1] = dp[y-1][x+1][1] + 1;
                }
                
                min = Math.min(dp[y][x][0], dp[y][x][1]);
                while(ans < min) {
                	if(dp[y-min+1][x-min+1][1] >= min && dp[y-min+1][x+min-1][0] >= min)
                		ans = Math.max(ans, min);
                	min--;
                }
                
            }
        }
        
        System.out.println(ans);
    }
}