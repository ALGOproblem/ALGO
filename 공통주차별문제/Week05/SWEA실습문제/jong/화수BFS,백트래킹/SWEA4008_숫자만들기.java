
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA4008_숫자만들기 {
	static int[] op;  // 연산자 개수 저장 배열 (+, -, *, / 순서)
	static int N;  // 숫자의 개수
	static char[] oper;  // 선택된 연산자 순서 저장 배열
	static Queue<Integer> qu;  // 입력받은 숫자들을 저장할 큐
	static int max;  // 최댓값 저장 변수
	static int min;  // 최솟값 저장 변수
	static char[] operation = {'+', '-', '*', '/'};  // 연산자 종류

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());  // 숫자 개수 입력

			op = new int[N + 1];  // 연산자 개수 저장 배열
			qu = new LinkedList<>();  // 숫자 저장할 큐

			StringTokenizer st = new StringTokenizer(br.readLine());
			op[0] = Integer.parseInt(st.nextToken()); // '+' 개수
			op[1] = Integer.parseInt(st.nextToken()); // '-' 개수
			op[2] = Integer.parseInt(st.nextToken()); // '*' 개수
			op[3] = Integer.parseInt(st.nextToken()); // '/' 개수

			// 숫자 입력 받아 큐에 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				qu.offer(Integer.parseInt(st.nextToken()));
			}

			oper = new char[N];  // 연산자 순서를 저장할 배열
			max = Integer.MIN_VALUE;  // 최댓값 초기화
			min = Integer.MAX_VALUE;  // 최솟값 초기화

			back(0);  // 백트래킹 시작

			System.out.println("#" + tc + " " + (max - min));  // 최댓값 - 최솟값 출력
		}
	}

	// 연산자 조합을 백트래킹으로 순열 생성
	public static void back(int depth) {
		if (depth == N - 1) {  // 연산자 순서가 다 정해졌을 경우
			int result = cal();  // 계산 결과
			max = Math.max(max, result);  // 최댓값 갱신
			min = Math.min(min, result);  // 최솟값 갱신
		} else {
			for (int i = 0; i < 4; i++) {
				if (op[i] > 0) {  // 해당 연산자가 남아있다면
					op[i]--;  // 사용 처리
					oper[depth] = operation[i];  // 연산자 배치
					back(depth + 1);  // 다음 깊이로 재귀 호출
					op[i]++;  // 사용 취소 (백트래킹 복원)
				}
			}
		}
	}

	// 선택된 연산자 배열(oper)에 따라 계산 수행
	public static int cal() {
		Queue<Integer> copyqu = new LinkedList<>(qu);  // 숫자 큐 복사
		int result = copyqu.poll();  // 첫 숫자 꺼냄

		for (int i = 0; i < N - 1; i++) {
			int a = copyqu.poll();  // 다음 숫자 꺼냄
			switch (oper[i]) {  // 연산자에 따라 계산
				case '+':
					result += a;
					break;
				case '-':
					result -= a;
					break;
				case '*':
					result *= a;
					break;
				case '/':
					result /= a;
					break;
			}
		}

		return result;  // 계산 결과 반환
	}
}