
// 결국 못 풀어서 GPT의 help
// 처음에는 BackTracking 에 memo[][] 사용하는 거라는 느낌을 받음
// 근데 visited로 하다가 틀림
// 이런 문제의 경우 '여러 번 방문 가능함' -> boolean이 아니라 (r, c)에서 목표 지점까지 갈 수 있는 경로의 수”를 담는 dp 배열을 사용해야 함


import java.util.*;

public class Main {
    static int M, N;
    static int[][] map;
    static int[][] dp;  // dp[r][c]: (r, c)에서 (M-1, N-1)까지 가는 내리막 경로의 수
    static boolean[][] visited;
    
    // 상, 하, 좌, 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        dp = new int[M][N];
        // dp 초기값을 -1로 세팅해서 "계산 안 됨"을 표시
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // (0,0) 에서 시작해서 (M-1, N-1)까지 가는 경로 수 구하기
        int answer = dfs(0, 0);
        
        System.out.println(answer);
        sc.close();
    }
    
    static int dfs(int r, int c) {
        // 만약 목표 지점에 도달했다면 경로 1개
        if (r == M - 1 && c == N - 1) {
            return 1;
        }
        
        // 이미 계산한 적이 있으면 바로 반환
        if (dp[r][c] != -1) {
            return dp[r][c];
        }
        
        // dp[r][c] = 0 으로 초기화 후, 갈 수 있는 방향을 모두 탐색
        dp[r][c] = 0;
        
        // 현재 위치의 고도
        int currentHeight = map[r][c];
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            // 범위를 벗어나지 않고, 높이가 낮은 지점만 이동
            if (nr >= 0 && nr < M && nc >= 0 && nc < N) {
                if (map[nr][nc] < currentHeight) {
                    dp[r][c] += dfs(nr, nc); // 이걸 어떻게 바로바로 생각하지...??
                }
            }
        }
        
        return dp[r][c];
    }
}
