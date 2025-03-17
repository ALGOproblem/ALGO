package 공통주차별문제.Week03.MIRIM;

import java.util.Scanner;

public class 백준20057_마법사상어와토네이도 {
    static int N;
    static int[][] map;
    static int[] dx = new int[] {0, 1, 0, -1}; // 좌 하 우 상
    static int[] dy = new int[] {-1, 0, 1, 0}; // 좌 하 우 상
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        tornado(0, 1, 1, N / 2, N / 2);

        System.out.println(answer);
    }

    static void tornado(int idx, int cycle, int distance, int r, int c) {
        if (r == 0 && c == 0) return; //종료조건

        distance--;

        int dr = dx[idx];
        int dc = dy[idx];

        int nr = r + dr;
        int nc = c + dc;

        if (nr < 0 || nc < 0 || nr >= N || nc >= N) return;

        int sand = map[nr][nc];

        // 비율 나누기 -> 남은 건 a로 이동
        int remain = proportional(r, c, idx, sand);

        map[nr][nc] = 0;

        if (nr + dr >= 0 && nc + dc >= 0 && nr + dr < N && nc + dc < N) {
            map[nr + dr][nc + dc] += remain;
        } else {
            answer += remain;
        }

        // 같은 방향으로 추가 이동
        if (distance > 0) {
            tornado(idx, cycle, distance, nr, nc);
        } else {
            idx++;

            // 인덱스 주기
            if (idx == 4) {
                cycle++;
                idx %= 4;
            }

            if (idx == 0 || idx == 1) distance = 2 * cycle - 1;
            else distance = 2 * cycle;

            tornado(idx, cycle, distance, nr, nc);
        }

    }

    static int proportional(int r, int c, int idx, int sand) {
        int[][] arr = new int[][] {
                {1, 0, 1}, // 비율, 이동방향에서 거리, 이동방향의 수직방향에서 거리
                {2, 1, 2},
                {7, 1, 1},
                {10, 2, 1},
                {5, 3, 0}
        };

        int remain = sand;

        for (int i = 0; i < arr.length; i++) {
            int percent = arr[i][0];
            int idxMove = arr[i][1];
            int delta = arr[i][2];

            int divided = (int) Math.floor(sand * (percent / 100.0));

            if (delta == 0)  {
                int nr1 = r + dx[idx] * idxMove;
                int nc1 = c + dy[idx] * idxMove;

                if (nr1 >= 0 && nc1 >= 0 && nr1 < N && nc1 < N) {
                    map[nr1][nc1] += divided;
                } else {
                    answer += divided;
                }

                remain -= divided;
                continue;
            }

            for (int j = 0; j < 2; j++) {

                int idx1 = (idx + (j == 0 ? 1: -1) + 4) % 4;
                int nr1 = r + dx[idx] * idxMove + dx[idx1] * delta;
                int nc1 = c + dy[idx] * idxMove + dy[idx1] * delta;

                if (nr1 >= 0 && nc1 >= 0 && nr1 < N && nc1 < N) {
                    map[nr1][nc1] += divided;
                } else {
                    answer += divided;
                }

                remain -= divided;
            }

        }
        return remain;

    }// proportional
}
