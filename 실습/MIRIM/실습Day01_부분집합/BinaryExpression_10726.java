package 실습.MIRIM.실습Day01_부분집합;

import java.util.Scanner;

public class BinaryExpression_10726 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            long M = sc.nextLong();

            long power = (long) Math.pow(2, N);
            long m = 0;
            if (M != 0) m = M % power;

            String answer = "ON";
            for (int i = 0; i < N; i++) {
                if ((m & (1<<i)) == 0) {
                    answer = "OFF";
                    break;
                }
            }
            System.out.printf("#%d %s\n", tc, answer);

        }// tc
        sc.close();
    }
}
