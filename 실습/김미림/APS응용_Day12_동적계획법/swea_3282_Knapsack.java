package 실습.김미림.APS응용_Day12_동적계획법;

import java.util.Scanner;

public class swea_3282_Knapsack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt();

			int[] vol = new int[N + 1];
			int[] value = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				vol[i] = sc.nextInt();
				value[i] = sc.nextInt();
			}

			int[][] dp = new int[N + 1][K + 1];

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= K; j++) {
					
					if (j >= vol[i]) {
						
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - vol[i]] + value[i]);
					
					} else {
						
						dp[i][j] = dp[i - 1][j];
					}
				}
			}

			System.out.printf("#%d %d\n", tc, dp[N][K]);

		} // tc
		sc.close();
	}// main

}
