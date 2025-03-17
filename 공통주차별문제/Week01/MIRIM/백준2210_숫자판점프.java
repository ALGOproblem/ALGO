package 공통주차별문제.Week01.MIRIM;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 백준2210_숫자판점프 {
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Set<Integer> numbers = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = sc.nextInt();
            }
        }// 입력


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 0, 0);
            }
        }

        System.out.println(numbers.size());
    }// main

    static void dfs(int r, int c, int count, int value) {
        count++;
        if (r < 0 || r >= map.length || c < 0 || c >= map[0].length || count > 6) return;

        value = value * 10 + map[r][c];
        if (count == 6) {
            numbers.add(value);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length) continue;
            dfs(nr, nc, count, value);
        }
    }// dfs
}
