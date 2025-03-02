package samsungA형대비.MIRIM.rollTheDice_14499;

import java.util.Scanner;

public class RollTheDice_14499 {
    static int[] dice = new int[6]; // 윗면, 북, 동, 서, 남, 바닥 // 서로 마주보는 칸끼리 연결 가능
    static int r;
    static int c;
    static int N;
    static int M;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        int K = sc.nextInt();

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        } // 입력

        for (int k = 0; k < K; k++) {
            int dir = sc.nextInt();
            roll(dir);
        } // 실행

    }// main

    static void roll(int dir) {
        int idx = 0, nr = r, nc = c;

        switch (dir) {
            case (1): // 동쪽
                idx = 2;
                nc++;
                break;
            case (2): // 서쪽
                idx = 3;
                nc--;
                break;
            case (3): // 북쪽
                idx = 1;
                nr--;
                break;
            case (4): // 남쪽
                idx = 4;
                nr++;
        }

        if (0 <= nr && nr < N && 0 <= nc && nc < M) {
            // 이동
            r = nr;
            c = nc;

            // 값 교환하기
            int val1 = dice[idx];
            dice[idx] = dice[0];

            int val2 = dice[5];
            dice[5] = val1;

            val1 = dice[5 - idx];
            dice[5 - idx] = val2;

            dice[0] = val1;

            // 칸과 바닥면 사이 복사
            if (map[r][c] == 0)
                map[r][c] = dice[5];
            else {
                dice[5] = map[r][c];
                map[r][c] = 0;
            }

            System.out.println(dice[0]); // 이동 시 상단의 값 출력
        } // 이동

    } // roll

}