package 공통주차별문제.Week05.SWEA실습문제.TERRY;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class swea4008숫자만들기 {

	// 계산 함수

	static BufferedReader input;
	static StringTokenizer st;
	static int N, max, min;
	static int[] operator, selected;
	static int[] numbers;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		// - 조건
		// 3초
		// 3 <= N 숫자 개수 <= 12
		// 카드 개수 총합은 N - 1
		// 1<= 숫자 <= 9
		// 주어진 연산자 다 써야 됨
		// 숫자 사이에 연산자는 1개
		// 수식 계산 왼쪽에서 오른쪽으로 -> queue 사용
		// 나누기 소수점 버리기 -> int
		// 숫자 순서 변경 X
		// 연산 값은 -100_000_000 <= 값 <= 100_000_000 보장 -> int 값 됨

		input = new BufferedReader(new InputStreamReader(System.in));
	//	input = new BufferedReader(new FileReader("input.txt"));
		int T = Integer.parseInt(input.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(input.readLine());

			// 연산자 세팅
			operator = new int[4];
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}

			selected = new int[N - 1];

			// 숫자 세팅
			numbers = new int[N];
			st = new StringTokenizer(input.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			dfs(0);

			System.out.println("#" + t + " " + (max - min));
		}
	}

	private static void calResult() {
		// 숫자 계산
		int result = numbers[0];
		for (int i = 0; i < N - 1; i++) {
			int nextNum = numbers[i + 1];
			switch (selected[i]) {
			case 0: // +
				result += nextNum;
				break;
			case 1: // -
				result -= nextNum;
				break;
			case 2: // *
				result *= nextNum;
				break;
			case 3: // /
				result /= nextNum;
				break;
			}
		}

		// 최댓값과 최솟값 업데이트
		max = Math.max(max, result);
		min = Math.min(min, result);
	}

	// 연산자 조합 만들기
	private static void dfs(int level) {
		if (level == N - 1) {
			calResult();
			return;
		}

		// 연산자 리스트 길이만큼
		for (int i = 0; i < 4; i++) {
			if (operator[i] == 0)
				continue;

			selected[level] = i;
			operator[i]--;
			dfs(level + 1);
			operator[i]++;
		}

	}

}
