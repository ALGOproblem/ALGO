package 공통주차별문제.Week07.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14889_스타트와링크 {
	static BufferedReader input;
	static StringTokenizer st;

	static int N;
	static int[][] matrix;
	static boolean[] selected;
	static int min;

	// - 조합 문제
	// 1. 팀 조합구하기
	// 2. 인원의 절반이 한 팀을 구성했나요?
	// 3. 팀의 능력치 차이 계산
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("input.txt"));

		N = Integer.parseInt(input.readLine());

		matrix = new int[N][N];
		selected = new boolean[N];

		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		recur(0);
		System.out.println(min);
	}

	static void recur(int level) {
		if (level == N) {
			// 계산 함수 실행
			cal();
			return;
		}

		// 조합하기
		for (int i = level; i < N; i++) {
			selected[i] = true;
			recur(i + 1);
			selected[i] = false;
		}

	}

	// 선택된 것들 계산
	static void cal() {
		// 한 팀이 인원의 절반일 경우에만 계산
		if (!isTeam()) {
			return;
		}

		int trueSum = 0;
		int falseSum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				// 둘 다 참 팀일 때
				if (selected[i] && selected[j]) {
					trueSum += matrix[i][j] + matrix[j][i];
				}
				// 둘다 거짓 팀일 때
				if (!selected[i] && !selected[j]) {
					falseSum += matrix[i][j] + matrix[j][i];
				}
			}
		}

		min = Math.min(min, Math.abs(trueSum - falseSum));
	}

	// 한 팀이 인원의 절반인가?
	static boolean isTeam() {
		int trueCnt = 0;
		// forEach로도 가능
		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				trueCnt++;
			}
		}

		// 절반 아니다
		if (trueCnt != N / 2) {
			return false;
		}

		// 절반 맞다.
		return true;
	}
}
