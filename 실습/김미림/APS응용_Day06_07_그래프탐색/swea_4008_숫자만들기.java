package 실습.김미림.APS응용_Day06_07_그래프탐색;

import java.util.*;

public class swea_4008_숫자만들기 {
    static int N;
    static int[] ops;
    static int[] nums;
    static int max, min;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();

            ops = new int[4];
            for (int i = 0; i < 4; i++) {
                ops[i] = sc.nextInt();
            }

            nums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = sc.nextInt();
            }

            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            dfs(0, nums[0]);

            System.out.printf("#%d %d\n", tc, max - min);

        }// tc
        sc.close();
    }// main

    static void dfs(int depth, int cal) {
        if (depth == N - 1) {
            max = Math.max(max, cal);
            min = Math.min(min, cal);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (ops[i] == 0) continue;

            int newCal = calculate(cal, nums[depth + 1], i);
            ops[i]--;
            dfs(depth + 1, newCal);

            // 원상 복귀
            ops[i]++;
        }
    }

    static int calculate(int a, int b, int op) {
        switch(op) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            default:
                return a / b;
        }
    }
}
