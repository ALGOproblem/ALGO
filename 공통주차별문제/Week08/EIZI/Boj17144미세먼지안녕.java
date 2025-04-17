import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[][] arr;
    // 확산용 방향 배열 (상, 우, 하, 좌)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    // 공기청정기 위치 (두 칸)
    static int airTopX = -1, airTopY = -1;
    static int airBotX = -1, airBotY = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[R][C];

        // 입력 받고 공기청정기 위치 찾기
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    if (airTopX == -1) {
                        airTopX = i;
                        airTopY = j;
                    } else {
                        airBotX = i;
                        airBotY = j;
                    }
                }
            }
        }

        // T초 동안 시뮬레이션
        for (int t = 0; t < T; t++) {
            diffuse();
            cleanTop();
            cleanBottom();
        }

        // 남은 미세먼지 합 구하기
        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) answer += arr[i][j];
            }
        }
        System.out.println(answer);
    }

    // 1) 미세먼지 확산
    static void diffuse() {
        int[][] tmp = new int[R][C];
        // 공기청정기 위치는 그대로
        tmp[airTopX][airTopY] = -1;
        tmp[airBotX][airBotY] = -1;

        // 원본 arr을 읽어서 tmp에만 쌓기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) {
                    int spread = arr[i][j] / 5;
                    int count = 0;
                    // 네 방향 검사
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d], ny = j + dy[d];
                        if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                        if (arr[nx][ny] == -1) continue;
                        tmp[nx][ny] += spread;
                        count++;
                    }
                    // 남은 먼지
                    tmp[i][j] += arr[i][j] - spread * count;
                }
            }
        }

        arr = tmp;
    }

    // 2) 위쪽 공기청정기 작동 (반시계 방향)
    static void cleanTop() {
        int x = airTopX;

        // ① 위쪽 세로 (밑에서 위로 당겨오기)
        for (int i = x - 1; i > 0; i--) {
            arr[i][0] = arr[i - 1][0];
        }
        // ② 윗 행 (왼쪽→오른쪽)
        for (int j = 0; j < C - 1; j++) {
            arr[0][j] = arr[0][j + 1];
        }
        // ③ 오른쪽 세로 (위→아래)
        for (int i = 0; i < x; i++) {
            arr[i][C - 1] = arr[i + 1][C - 1];
        }
        // ④ 공기청정기 행 (오른쪽→왼쪽)
        for (int j = C - 1; j > 1; j--) {
            arr[x][j] = arr[x][j - 1];
        }
        // 청정기 바로 옆은 깨끗한 바람
        arr[x][1] = 0;
    }

    // 3) 아래쪽 공기청정기 작동 (시계 방향)
    static void cleanBottom() {
        int x = airBotX;

        // ① 아래쪽 세로 (위에서 아래로 당겨오기)
        for (int i = x + 1; i < R - 1; i++) {
            arr[i][0] = arr[i + 1][0];
        }
        // ② 아랫 행 (왼쪽→오른쪽)
        for (int j = 0; j < C - 1; j++) {
            arr[R - 1][j] = arr[R - 1][j + 1];
        }
        // ③ 오른쪽 세로 (아래→위)
        for (int i = R - 1; i > x; i--) {
            arr[i][C - 1] = arr[i - 1][C - 1];
        }
        // ④ 공기청정기 행 (오른쪽→왼쪽)
        for (int j = C - 1; j > 1; j--) {
            arr[x][j] = arr[x][j - 1];
        }
        // 청정기 바로 옆은 깨끗한 바람
        arr[x][1] = 0;
    }
}
