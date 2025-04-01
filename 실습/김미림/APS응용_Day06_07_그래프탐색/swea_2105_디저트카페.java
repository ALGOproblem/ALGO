package 실습.김미림.APS응용_Day06_07_그래프탐색;

import java.util.*;

public class swea_2105_디저트카페 {
    static int N, max;
    static int[][] map;
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};
    static int startr;
    static int startc;
    static boolean[][] visited;
    static boolean[] dessert;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }// 입력

            max = 0;
            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    visited = new boolean[N][N];
                    dessert = new boolean[101];
                    startr = i;
                    startc = j;
                    dfs(i, j, 0, 0, 0);
                }
            }
            if (max == 0) max = -1;
            System.out.printf("#%d %d\n", tc, max);
        }// tc
        sc.close();
    }// main

    static void dfs(int r, int c, int dir, int count, int turn) {
        if (r == startr & c == startc && count >= 4  && turn >= 3) {
            max = Math.max(max, count);
            return;
        }

        for (int d = dir; d <= dir + 1 && d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

            int value = map[nr][nc];
            if (!visited[nr][nc] && !dessert[value]) {
                // 방문 체크
                visited[nr][nc] = true;
                dessert[value] = true;
                dfs(nr, nc, d, count + 1, d == dir? turn : turn + 1);

                // 원상 복귀
                visited[nr][nc] = false;
                dessert[value] = false;
            }
        }

    }//dfs

}

