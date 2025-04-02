import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952_수영장 {
	static int N;
	static int M;	
	static int one;       // 1일 이용권 요금
	static int month;     // 1개월 이용권 요금
	static int month3;    // 3개월 이용권 요금
	static int year;      // 1년 이용권 요금
	static int[] monthact;  // 각 달별 이용 계획 (몇 번 이용할지)
	static int min;       // 최소 비용 저장용 변수

	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력

		for(int tc = 1; tc <= T; tc++) {

			// 요금제 입력: 1일, 1달, 3달, 1년
			StringTokenizer st = new StringTokenizer(br.readLine());
			one = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			month3 = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());

			// 1월부터 12월까지의 이용 계획 입력
			monthact = new int[13];  // 1~12월 사용, 인덱스 1부터 사용
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= 12; i++) {
				monthact[i] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;  // 최소값 초기화

			// 백트래킹 시작: 1월부터 시작, 초기 요금은 0
			back(1, 0);

			// 연간 이용권과 비교해서 더 작은 비용 선택
			min = Math.min(min, year);

			System.out.println("#" + tc + " " + min);  // 결과 출력
		}
	}

	// 백트래킹 함수: 현재 달(depth)과 지금까지의 요금(sum)
	public static void back(int depth, int sum) {
		if(depth >= 13) {  // 12월을 넘어가면 (끝까지 봤다면)
			min = Math.min(min, sum);  // 최소 요금 갱신
		} else {
			// ① 1일권으로 계산: 이용 일수 * 1일 요금
			back(depth + 1, sum + one * monthact[depth]);

			// 만약 이번 달에 이용 계획이 있다면,
			if(monthact[depth] != 0) {
				// ② 1개월권 사용
				back(depth + 1, sum + month);

				// ③ 3개월권 사용
				back(depth + 3, sum + month3);
			}
		}
	}
}