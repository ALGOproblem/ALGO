package 실습.MIRIM.APS응용_Day06_07_그래프탐색;

import java.util.*;

public class swea_7733_치즈도둑 {
    static int N;
    static int[][] map;
    static int maxSize;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            map = new int[N][N];
            maxSize = 1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            } // 입력

            for (int d = 1; d <= 100; d++)
                calByDay(d);

            System.out.printf("#%d %d\n", tc, maxSize);
        } // tc

        sc.close();

    }// main

    static void calByDay(int day) {
        int[][] group = new int[N][N];
        int groupIdx = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > day && group[i][j] == 0) {
                    bfsForGroup(day, i, j, group, groupIdx++);
                }
            }
        }

        maxSize = Math.max(maxSize, groupIdx - 1);

    }

    static void bfsForGroup(int day, int sr, int sc, int[][] group, int groupIdx) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { sr, sc });
        group[sr][sc] = groupIdx;

        int[] dr = { 1, 0, -1, 0 };
        int[] dc = { 0, 1, 0, -1 };

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                    continue;
                if (map[nr][nc] <= day)
                    continue;

                if (group[nr][nc] == 0) {
                    queue.offer(new int[] { nr, nc });
                    group[nr][nc] = groupIdx;
                }
            }
        } // while

    }// bfsForGroup
}
