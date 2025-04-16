package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class swea_1970_쉬운거스름돈 {
	static BufferedReader input;
	static int[] coins = new int[] { 5_000, 1_000, 500, 100, 50, 10, 5, 1 };

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(input.readLine());

			int customerMoney = N / 10;
			int[] counts = new int[8]; // 지폐 카운트

			// dp 인덱스는 값 / 10 이고, 값은 총 지폐 개수를 의미
			// 마지막 자리수가 항상 0이니 적은 계산을 위해 10을 나누기
			int[] dp = new int[customerMoney + 1]; // N / 10 원까지
			Arrays.fill(dp, N + 1); // 최댓값으로 초기화
			dp[0] = 0;

			for (int coin : coins) {
				// dp 갱신
				for (int j = coin; j < customerMoney + 1; j++) {
					dp[j] = Math.min(dp[j], dp[j - coin] + 1);
				}
			}

			// 사용한 지폐 카운팅
			int remaining = customerMoney;
			for (int i = 0; i < coins.length; i++) {
				// 남은 돈이 지폐보다 크거나 같고 && dp 값이 지폐 사용 전 값 + 1 일 때
				while (remaining >= coins[i] && dp[remaining] == dp[remaining - coins[i]] + 1) {
					counts[i]++;
					remaining -= coins[i];
				}
			}

			StringBuilder result = new StringBuilder("#" + t + "\n");

			for (int count : counts) {
				result.append(count + " ");
			}
			System.out.println(result);
		}

	}
}
