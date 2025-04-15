package 공통주차별문제.Week07.공통문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_14888_연산자끼워넣기 {
	static BufferedReader input;
	static StringTokenizer st;

	static int N; // 숫자 개수
	static int min, max;
	static int[] numbers, operators, selected; // 숫자, 연산자, 선택한 연산자 배열

	// 순열
	// - 음수를 나눌 시 양수로 변환 후 나누고 음수로 변환

	// 1. 주어진 연산자로 모든 집합 구하기
	// 2. 마지막에 계산하기
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		// 2 <= N <= 11
		N = Integer.parseInt(input.readLine());

		// 숫자 배열 초기화
		numbers = new int[N];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		// 연산자 초기화
		st = new StringTokenizer(input.readLine());
		operators = new int[4];
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}

		selected = new int[N];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		// 연산자 조합 구하기
		recur(0);

		System.out.println(max);
		System.out.println(min);
	} // main

	static void recur(int level) {
		if (level == N - 1) {
			cal();
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operators[i] == 0) {
				continue;
			}

			selected[level] = i;
			operators[i]--;
			recur(level + 1);
			operators[i]++;
		}
	}

	static void cal() {
		int result = numbers[0];
		for (int i = 0; i < N - 1; i++) {
			int nextNum = numbers[i + 1];
			// 연산자 넣기
			// + , - , * , /
			switch (selected[i]) {
			case 0:
				result += nextNum;
				break;
			case 1:
				result -= nextNum;
				break;
			case 2:
				result *= nextNum;
				break;
			case 3:
				result /= nextNum;
				break;
			}
		}
		
		min = Math.min(min, result);
		max = Math.max(max, result);
	} // cal
} // class
