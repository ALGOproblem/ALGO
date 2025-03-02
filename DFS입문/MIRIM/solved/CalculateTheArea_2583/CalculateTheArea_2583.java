package DFS입문.MIRIM.solved.CalculateTheArea_2583;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CalculateTheArea_2583 {
    static int M; // 열의 개수
    static int N; // 행의 개수
    static int K; // 직사각형 개수
    static int[][] map;
    static int count;
    static ArrayList<Integer> result;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        K = sc.nextInt();

        // 입력 반대로 들어옴 미친놈 -> 행과 열 뒤집어서 보기
        // 어쨌든 탐색임
        map = new int[N][M];
        for (int i = 0; i < K; i++) {
            int xBottom = sc.nextInt();
            int yBottom = sc.nextInt();
            int xLimit = sc.nextInt();
            int yLimit = sc.nextInt();

            for (int x = xBottom; x < xLimit; x++) {
                for (int y = yBottom; y < yLimit; y++) {
                    map[x][y] = 1;
                }
            }
        } // 입력

        result = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                count = 0;
                if (map[i][j] == 0) {
                    dfs(i, j);
                    result.add(count);
                }
            }
        } // 탐색

        Collections.sort(result);
        System.out.println(result.size());
        for (Integer i : result) {
            System.out.print(i + " ");
        }

    }// main

    static void dfs(int r, int c) {
        if (map[r][c] == 0) {
            count++;
            map[r][c] = 1;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;
                dfs(nr, nc);
            }
        }
    }
}
