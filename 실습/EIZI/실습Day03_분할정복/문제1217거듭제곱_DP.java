package 실습.EIZI.실습Day03_분할정복;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 문제1217거듭제곱_DP {
    static int[] dp;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/문제1217거듭제곱.txt"));
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int test = sc.nextInt();
            int A = sc.nextInt();
            int N = sc.nextInt();
            dp = new int[N + 1]; // DP 배열 선언 (0 ~ N까지)
            dp[0] = 1;  // Base Case: A^0 = 1

            int answer = pow(A, N);
            System.out.println("#" + test + " " + answer);
        }
    }

    public static int pow(int A, int N) {
        if (dp[N] != 0) return dp[N]; // 이미 계산된 값이 있다면 사용

        if (N % 2 == 0) {
            int tmp = pow(A, N / 2);
            dp[N] = tmp * tmp;  // 값 저장
        } else {
            int tmp = pow(A, (N - 1) / 2);
            dp[N] = tmp * tmp * A;  // 값 저장
        }
        return dp[N]; // 저장된 값 반환
    }
}
