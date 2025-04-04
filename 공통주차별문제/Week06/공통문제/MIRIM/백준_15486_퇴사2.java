package 공통주차별문제.Week06.공통문제.MIRIM;

import java.util.Scanner;

public class 백준_15486_퇴사2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] time = new int[N + 1];
		int[] price = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			time[i] = sc.nextInt();
			price[i] = sc.nextInt();
		}// 입력
		
		int[] dp = new int[N + 2]; //N + 1일차에 정산하자
		
		for (int i = 1; i <= N; i++) {
			int t = time[i];// 소요 시간
			
			dp[i] = Math.max(dp[i], dp[i - 1]);
			
			// 해당 일의 상담 비교
			if (i + t <= N + 1)
				dp[i + t] = Math.max(dp[i + t], dp[i] + price[i]);
		
		}
		dp[N + 1] = Math.max(dp[N], dp[N + 1]); // 최종 정산
		
		System.out.println(dp[N + 1]);
		
		sc.close();
	}//main
	
}
