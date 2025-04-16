package 실습.MIRIM.실습Day04_백트래킹;

import java.util.Scanner;

public class CardGameByKyuYoung_6808 {
	static int[] card1;
	static int[] card2;
	static int winningNumber;
	static int losingNumber;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			card1 = new int[9];
			card2 = new int[9];
			boolean[] card = new boolean[18 + 1];

			int sum1 = 0, sum2 = 0;
			for (int i = 0; i < 9; i++) {
				card1[i] = sc.nextInt();
				card[card1[i]] = true;
				sum1 += card1[i];
			} // 입력

			int idx2 = 0;
			for (int i = 1; i <= 18; i++) {
				if (!card[i]) {
					card2[idx2++] = i;
					sum2 += i;
				}
			} // 다른 카드 입력

			winningNumber = 0;
			losingNumber = 0;

			backtrack(0, 0, 0, new boolean[9], sum1, sum2);
			System.out.printf("#%d %d %d\n", tc, winningNumber, losingNumber);

		} // tc
		sc.close();
	}// main

	static void backtrack(int start, int score1, int score2, boolean[] visited2, int sum1, int sum2) {
		if (start == 9) {
			if (score1 > score2)
				winningNumber++;
			else if (score1 < score2)
				losingNumber++;
			return;
		} // 종료 조건

		if (score1 + sum1 + sum2 < score2) {
			losingNumber += factorial(9 - start);
			return;
		} else if (score2 + sum1 + sum2 < score1) {
			winningNumber += factorial(9 - start);
			return;
		}

		for (int idx2 = 0; idx2 < 9; idx2++) {
			if (visited2[idx2])
				continue;

			visited2[idx2] = true;

			int one = card1[start];
			int two = card2[idx2];
			int total = one + two;

			// idx2를 사용하고 다음으로
			if (one > two) {
				backtrack(start + 1, score1 + total, score2, visited2, sum1 - one, sum2 - two);
			} else if (one < two) {
				backtrack(start + 1, score1, score2 + total, visited2, sum1 - one, sum2 - two);
			} else {
				backtrack(start + 1, score1, score2, visited2, sum1 - one, sum2 - two);
			}

			// 원상복귀
			visited2[idx2] = false;
		}

	}

	static int factorial(int n) {
		int answer = 1;
		for (int i = n; i > 0; i--) {
			answer *= i;
		}
		return answer;
	}

}
