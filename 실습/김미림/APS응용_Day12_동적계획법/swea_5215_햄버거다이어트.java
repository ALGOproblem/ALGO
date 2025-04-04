package 실습.김미림.APS응용_Day12_동적계획법;

import java.util.Scanner;

public class swea_5215_햄버거다이어트 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 재료의 수
			int L = sc.nextInt(); // 제한 칼로리
			
			int[] score = new int[N + 1];
			int[] weight = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				score[i] = sc.nextInt();
				weight[i] = sc.nextInt();
			}// 재료 정보 입력(점수, 칼로리)
			
			int[][] dp = new int[N + 1][L + 1];
			
			for (int i = 1; i <= N; i++) { // 행: 음식
				for (int j = 1; j <= L; j++) { // 열 : 칼로리
					
					if (j >= weight[i]) {
						// 선택 하지 않음과 선택함을 비교한다
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + score[i]);
					
					} else {
						// 해당 칼로리보다 음식 칼로리가 더 클 때 -> 아예 입력 불가
						dp[i][j] = dp[i - 1][j];
					}
					
				}
			}
			
			System.out.printf("#%d %d\n", tc, dp[N][L]);
		}// tc
		sc.close();
	}//main

}
