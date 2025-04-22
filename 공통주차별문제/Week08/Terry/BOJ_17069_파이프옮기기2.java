package 공통주차별문제.Week08.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17069_파이프옮기기2 {
	static BufferedReader input;
	static StringTokenizer st;
	
	// 각 요소에서 위치
	// 0, 1, 2 => 가로, 세로, 대각선
	// dp[row][col][0] = 가로
	// dp[row][col][1] = 세로
	// dp[row][col][2] = 대각선

    public static void main(String[] args) throws IOException {
    	input = new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("input.txt"));
    	
		// 초기화
        int N = Integer.parseInt(input.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] dp = new long[N][N][3]; // 0: 가로, 1: 세로, 2: 대각선
        
        // 행번호가 0이 아니라 1부터 시작한다고 함.
        // 조건 (1,1) (1,2)에서 가로 시작을 의미
        dp[0][1][0] = 1; 

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) continue; // 벽이면 넘어감

                // 가로로 도착 경우
                if (j + 1 < N && map[i][j + 1] == 0) {
                    dp[i][j + 1][0] += dp[i][j][0] + dp[i][j][2]; // 가로거나 대각선이거나
                }

                // 세로로 도착하는 경우
                if (i + 1 < N && map[i + 1][j] == 0) {
                    dp[i + 1][j][1] += dp[i][j][1] + dp[i][j][2]; // 세로거나 대각선이거나
                }

                // 대각선으로 도착하는 경우
                if (i + 1 < N && j + 1 < N && 
                		map[i + 1][j] == 0 &&
                		map[i][j + 1] == 0 && 
                		map[i + 1][j + 1] == 0) {
                    dp[i + 1][j + 1][2] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2]; // 가로 , 세로 , 대각선 모두 다 가능
                }
            }
        }

        System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
    }
}
