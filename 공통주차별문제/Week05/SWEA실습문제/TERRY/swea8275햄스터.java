package 공통주차별문제.Week05.SWEA실습문제.TERRY;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class swea8275햄스터 {
	static BufferedReader input;
	static StringTokenizer st;
	static int N, X, M;
	static int[][] records;
	static int[] hamHouse, resultHouse;
	static int maxSum;
	static StringBuilder result;

	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(input.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(input.readLine());

			// i번 우리에서 r번 우리까지 햄스터수가 s마리
			N = Integer.parseInt(st.nextToken()); // 우리 N개
			X = Integer.parseInt(st.nextToken()); // 각 우리 X마리 이하
			M = Integer.parseInt(st.nextToken()); // 경근이의 기록

			// 경근이 기록을 기록하기
			records = new int[M][3];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(input.readLine());
				records[i][0] = Integer.parseInt(st.nextToken());
				records[i][1] = Integer.parseInt(st.nextToken());
				records[i][2] = Integer.parseInt(st.nextToken());
			}

			hamHouse = new int[N];
			resultHouse = new int[N];
			maxSum = -1;

			recur(0, 0);

			result = new StringBuilder();

			result.append("#" + t + " ");

			if (maxSum == -1) {
				result.append(-1);
			} else {
				for (int h : resultHouse) {
					result.append(h + " ");
				}
			}

			System.out.println(result);

		}

	}

	private static void recur(int level, int sum) {
		if (level == N) {
			if (!isValid()) {
				return;
			}

			// max 갱신 및
			// 사전순 위치 확인
			if (sum > maxSum || (sum == maxSum && isEarlier())) {
				maxSum = sum;
				// 변경될 위험이 있어서 clone
				resultHouse = hamHouse.clone();
			}
			return;
		}

		// 우리 별 햄스터 조합 구하기
		for (int i = 0; i < X + 1; i++) {
			hamHouse[level] = i;
			recur(level + 1, sum + i);
		}

	}

	// 원하는 조건에 맞는지
	private static boolean isValid() {
		for (int[] record : records) {
			int sum = 0;
			int l = record[0] - 1; // 시작 우리
			int r = record[1] - 1; // 끝 우리
			int s = record[2]; // 합계

			for (int i = l; i <= r; i++) {
				sum += hamHouse[i];
			}

			if (sum != s) {
				return false;
			}

		}

		return true;
	}

	private static boolean isEarlier() {
		for (int i = 0; i < N; i++) {
			if (hamHouse[i] < resultHouse[i])
				return true;
			if (hamHouse[i] > resultHouse[i])
				return false;
		}
		return false;
	}
}
