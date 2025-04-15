package 공통주차별문제.Week07.EIZI;

import java.util.*;

public class Boj14888연산자끼워넣기 {

    static int N;
    static int[] A;
    static int plus, minus, multiply, divide;
    static int max_result = Integer.MIN_VALUE;
    static int min_result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        plus = sc.nextInt();
        minus = sc.nextInt();
        multiply = sc.nextInt();
        divide = sc.nextInt();

        dfs(1, A[0], plus, minus, multiply, divide);
        
        System.out.println(max_result);
        System.out.println(min_result);
        
        sc.close();
    }

    public static void dfs(int index, int current_result, int p, int m, int mul, int d) {
        if (index == N) {
            max_result = Math.max(max_result, current_result);
            min_result = Math.min(min_result, current_result);
            return;
        }

        if (p > 0) {
            dfs(index + 1, current_result + A[index], p - 1, m, mul, d);
        }
        if (m > 0) {
            dfs(index + 1, current_result - A[index], p, m - 1, mul, d);
        }
        if (mul > 0) {
            dfs(index + 1, current_result * A[index], p, m, mul - 1, d);
        }
        if (d > 0) {
            if (current_result < 0) { // 이 마이너스 조건을 생각하는게 좀 까다로웠따
                dfs(index + 1, -(-current_result / A[index]), p, m, mul, d - 1);
            } else {
                dfs(index + 1, current_result / A[index], p, m, mul, d - 1);
            }
        }
    }
}
