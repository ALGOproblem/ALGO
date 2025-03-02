package DFS입문.MIRIM.solved.단지번호붙이기_2667;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static int N;
    static int[][] map;
    static ArrayList<Integer> total;
    static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
    static int[] dc = { 0, 0, -1, 1 }; // 상하좌우
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 연결된 덩어리들 -> 이름 붙이기
        // 연결: 상하좌우만 인정, 대각선 아님
        // 각 단지에 속하는 집의 개수 -> 오름차순 출력
        N = sc.nextInt();
        sc.nextLine();
        map = new int[N][N];
        total = new ArrayList<Integer>();

        for (int i = 0; i < N; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                map[i][j] = c - '0';
            }
        } // 입력 받기

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count = 0;
                if (map[i][j] == 1) {
                    dfs(i, j);
                    total.add(count);
                }
            }
        } // 탐색

        Collections.sort(total);
        System.out.println(total.size());
        for (Integer i : total) {
            System.out.println(i);
        }

    }// main

    static void dfs(int r, int c) {
        if (map[r][c] == 1) {
            count++;
            map[r][c] = 0;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;
                dfs(nr, nc);
            }
        }
    }// dfs
}
