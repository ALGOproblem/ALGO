package 실습.김미림.실습Day01_부분집합;

import java.util.Scanner;

public class HamburgerDiet_5215 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 재료의 수
            int L = sc.nextInt(); // 제한 칼로리
            int[][] ingre = new int[N][2]; // 0: 점수, 1: 칼로리
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 2; j++) {
                    ingre[i][j] = sc.nextInt();
                }
            }

            int answer = 0;

            for (int i = 0; i < (1 << N); i++) {
                int score = 0;
                int cal = 0;

                for (int j = 0; j < N; j++) {
                    if ((i & (1 << j)) != 0) {
                        score += ingre[j][0];
                        cal += ingre[j][1];
                    }
                }

                if (cal <= L && answer < score) {
                    answer = score;
                }
            }

            System.out.printf("#%d %d\n", tc, answer);

        } // tc
        sc.close();
    }

}

