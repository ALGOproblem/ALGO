package 실습.MIRIM.실습Day01_부분집합;

import java.util.Scanner;

public class SumOfPartNumbers_2817 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();


        for (int tc = 1; tc <=T; tc++) {
            int N = sc.nextInt(); // n개의 자연수
            int K = sc.nextInt(); // 만들어야 하는 수

            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            } // 입력

            int count = 0;

            for (int i = 0; i < (1 << N); i++) {
                int sum = 0;
                for (int j = 0; j < N; j++) {
                    if ((i & (1 << j)) != 0) {
                        sum += arr[j];
                    }
                } // sum

                if (sum == K)
                    count++;
            }// 탐색

            System.out.printf("#%d %d\n", tc, count);

        }//tc

    }

}
