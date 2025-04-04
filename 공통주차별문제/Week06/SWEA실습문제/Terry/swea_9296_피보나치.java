package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_9296_피보나치 {
	static BufferedReader input;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int max = Integer.MIN_VALUE;

		int[] numbers = new int[5];

		// 입력
		for (int i = 0; i < 5; i++) {
			int number = Integer.parseInt(input.readLine());
			numbers[i] = number;

			max = Math.max(max, number);
		}

		int[] dp = new int[max + 1];
		dp[1] = 1;
		dp[2] = 1;

		// dp 계산
		for (int i = 3; i < max + 1; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		// 출력
		for (int i = 0; i < 5; i++) {
			System.out.println(dp[numbers[i]]);
		}

	}
}
