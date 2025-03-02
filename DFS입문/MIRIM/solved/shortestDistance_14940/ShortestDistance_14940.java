package DFS입문.MIRIM.solved.shortestDistance_14940;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ShortestDistance_14940 {
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];

        int row = 0;
        int column = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();

                if (map[i][j] == 2) {
                    row = i;
                    column = j;
                }
            }
        } // 입력

        bfs(row, column);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    map[i][j] = -1;
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }// 검증 -> 출력

    }// main

    static void bfs(int row, int column) {
        Queue<int[]> queue = new LinkedList<>();
        int[] dr = new int[] { -1, 1, 0, 0 };
        int[] dc = new int[] { 0, 0, -1, 1 };

        queue.add(new int[]{row, column});
        map[row][column] = 0;
        visited = new boolean[n][m];
        visited[row][column] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int distance = map[r][c];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && map[nr][nc] != 0) {
                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                    map[nr][nc] = distance + 1;
                }
            }

        }//탐색 끝

    }// bfs

}