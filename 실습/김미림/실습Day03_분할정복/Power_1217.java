package 실습.김미림.실습Day03_분할정복;

import java.util.Scanner;

public class Power_1217 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            tc = sc.nextInt();
            int N = sc.nextInt();
            int M = sc.nextInt();

            System.out.printf("#%d %d\n", tc, pow(N, M));
        }
    }// main

    static long pow(int N, int M) {
        if (M == 0)
            return 1;

        if (M % 2 == 0) {
            long tmp = pow(N, M / 2);
            return tmp * tmp;
        } else {
            long tmp = pow(N, (M - 1) / 2);
            return tmp * tmp * N;
        }

    }

}
