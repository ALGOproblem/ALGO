package 실습.MIRIM.APS응용_Day06_07_그래프탐색;

import java.util.*;

public class swea_1868_파핑파핑지뢰찾기 {
    static int N;
    static char[][] map;
    static int[][] near;
    static int minClick;
    static boolean[][] visited;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();

            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                String str = sc.next();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j);
                }
            }// 입력

            near = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int count = 0;
                    if (map[i][j] == '.') {
                        for (int d = 0; d < 8; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];

                            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                            if (map[nr][nc] == '*') count++;
                        }
                    }
                    near[i][j] = count;
                }
            } // 주변 지뢰 수 계산, 저장

            minClick = 0;
            visited = new boolean[N][N];
            bfs();
            System.out.printf("#%d %d\n", tc, minClick);

        }//tc
        sc.close();
    }//main

    static void bfs() {
        // 주변값이 0인 칸부터 클릭한다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.' && near[i][j] == 0 && !visited[i][j]) {
                    minClick++;
                    visited[i][j] = true;
                    openNearByZero(i, j);
                }// 방문하지 않은 주변값 0인 칸 방문
            }
        }// 0인 칸 클릭

        // 주변 값이 0이 아닌 칸 클릭하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.' && !visited[i][j]) {
                    visited[i][j] = true;
                    minClick++;
                }
            }
        }


    }

    static void openNearByZero(int r, int c) {
        for (int d = 0; d < 8; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

            if (visited[nr][nc] || map[nr][nc] == '*') continue;

            visited[nr][nc] = true;


            // 0이면 openNearByZero 다시 호출
            if (near[nr][nc] == 0) openNearByZero(nr, nc);
        }
    }//openNearByZero

}
