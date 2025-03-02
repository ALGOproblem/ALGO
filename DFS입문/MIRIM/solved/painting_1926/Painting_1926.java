package DFS입문.MIRIM.solved.painting_1926;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Painting_1926 {
    static int n;
    static int m;
    static int[][] map;
    static int count;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        } // 입력

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count = 0;

                if (map[i][j] == 1) {
                    dfs(i, j);
                    result.add(count);
                }
            }
        } // 탐색

        System.out.println(result.size());
        int max = 0;
        for (Integer num : result) {
            if (max < num)
                max = num;
        }
        System.out.println(max);

    }// main

    static void dfs(int r, int c) {
        if (map[r][c] == 0)
            return;

        count++;
        map[r][c] = 0;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                continue;
            dfs(nr, nc);
        }
    }// dfs
}
