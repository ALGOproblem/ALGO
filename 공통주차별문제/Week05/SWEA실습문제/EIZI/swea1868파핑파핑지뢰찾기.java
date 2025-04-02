
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 지뢰찾기 룰
 * 1. 가로, 세로, 대각선 8방향 중 지뢰가 하나도 없다면 해당 칸은 0
 * 2. 0인 칸을 선택하면 주변 8방향의 모든 안전 칸이 자동으로 드러남 (숫자가 0이 아닌 칸은 따로 클릭해야 함)
 * 
 * 알고리즘:
 * 1. 0인 칸들을 BFS로 묶어 한 번의 클릭으로 처리
 * 2. BFS 내에서 0인 칸의 8방향 모든 안전 칸을 visited 처리
 * 3. 남은 안전 칸은 개별적으로 클릭
 */

public class swea1868파핑파핑지뢰찾기 {
    static int N;
    static int[][] arr;      // 각 칸의 주변 지뢰 개수를 저장 (-1이면 지뢰)
    static char[][] board;   // 입력 보드 ('*'는 지뢰, '.'는 안전 칸)
    static boolean[][] visited; // 방문한(드러난) 칸 체크
    static int clickCount;   // 최소 클릭 횟수
    static int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1}; // 8방향: 상, 하, 좌, 우, 대각선들
    static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            N = Integer.parseInt(br.readLine());
            board = new char[N][N];
            arr = new int[N][N];
            visited = new boolean[N][N];
            clickCount = 0;
            
            // 보드 입력
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    board[i][j] = s.charAt(j);
                }
            }
            // 각 칸의 인접 지뢰 개수 계산 (-1이면 지뢰)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = countBomb(i, j);
                }
            }
            
            // 0인 칸에 대해 BFS 수행 (한 그룹당 1회 클릭)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 0 && !visited[i][j]) {
                        bfs(i, j);
                        clickCount++;  // 0 그룹 한 번 클릭 처리
                    }
                }
            }
            
            // 남은 안전 칸(지뢰가 아닌, 자동 처리되지 않은 칸) 개별 클릭
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && arr[i][j] != -1) {
                        clickCount++;
                    }
                }
            }
            
            System.out.println("#" + test + " " + clickCount);
        }
    }
    
    // 해당 칸 (x, y)의 인접 8방향에 있는 지뢰 개수를 계산 (지뢰면 -1 리턴)
    static int countBomb(int x, int y) {
        if (board[x][y] == '*') {
            visited[x][y] = true; // 지뢰는 이미 방문 처리
            return -1;
        }
        int bomb = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (board[nx][ny] == '*') bomb++;
        }
        return bomb;
    }
    
    // BFS로 0인 칸과 인접한 안전 칸(0이 아닌 칸 포함) 모두를 visited 처리
    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true; // 시작 셀 방문 처리
        queue.add(new int[] {x, y});
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            // 인접 8방향 검사
            for (int i = 0; i < 8; i++) {
                int nx = curr[0] + dx[i], ny = curr[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                // 안전 칸이며 아직 방문하지 않은 경우
                if (!visited[nx][ny] && arr[nx][ny] != -1) {
                    visited[nx][ny] = true;
                    // 인접 칸이 0이면 큐에 추가해 체인 반응 수행
                    if (arr[nx][ny] == 0) {
                        queue.add(new int[] {nx, ny});
                    }
                }
            }
        }
    }
}
