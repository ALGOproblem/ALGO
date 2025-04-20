// dp[r][c][d] = (r, c)를 파이프의 끝으로 하고, 방향이 d일 때 가능한 경로의 수
// dp배열 너무 어렵다.. 생각 못해서 결국 GPT 찬스


import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1]; // 1-based indexing
        dp = new int[N + 1][N + 1][3]; // 0: 가로, 1: 세로, 2: 대각선

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 상태: (1,1)-(1,2)
        dp[1][2][0] = 1;

        for (int r = 1; r <= N; r++) {
            for (int c = 3; c <= N; c++) {
                if (map[r][c] == 1) continue; // 벽은 이동 불가

                // 가로로 도달할 수 있는 경우
                dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2];

                // 세로로 도달할 수 있는 경우
                if (r > 1)
                    dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2];

                // 대각선으로 도달할 수 있는 경우
                if (r > 1 && map[r - 1][c] == 0 && map[r][c - 1] == 0)
                    dp[r][c][2] = dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2];
            }
        }

        // (N, N)에 도달할 수 있는 모든 방향의 합
        int result = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
        System.out.println(result);
    }
}
