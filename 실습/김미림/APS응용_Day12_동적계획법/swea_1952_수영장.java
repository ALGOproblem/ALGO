import java.util.Scanner;

public class swea_1952_수영장 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int[] price = new int[4]; // 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권
			int[] months = new int[13]; // 월 별 이용 횟수

			for (int i = 0; i < 4; i++) {
				price[i] = sc.nextInt();
			}

			for (int j = 1; j <= 12; j++) {
				months[j] = sc.nextInt();
			} // 입력

			int[] dp = new int[13]; // 최소 비용 계산

			for (int i = 1; i < 13; i++) {
				// 1일 이용권
				dp[i] = dp[i - 1] + months[i] * price[0];

				// 1달 이용권
				dp[i] = Math.min(dp[i], dp[i - 1] + price[1]);

				// 3달 이용권
				if (i >= 3)
					dp[i] = Math.min(dp[i], dp[i - 3] + price[2]);
				else
					dp[i] = Math.min(dp[i], price[2]); // 1, 2월에도 3달권 사용

				// 1년 이용권
				dp[12] = Math.min(dp[12], price[3]);

			} // dp

			System.out.printf("#%d %d\n", tc, dp[12]);

		} // tc
		sc.close();
	}// main

}
