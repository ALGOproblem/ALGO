import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA2105_디저트카페 {
	static int N;  // 지도 크기
	static int[][] arr;  // 디저트 카페 맵
	static Set<Integer> set;  // 먹은 디저트 종류 저장용 (중복 방지)
	static int[] diy = {1, 1, -1, -1};  // 대각선 이동 방향 (↘, ↙, ↖, ↗) - y좌표 변화
	static int[] dix = {1, -1, -1, 1};  // x좌표 변화
	static int max;  // 최대 디저트 수 저장

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());  // 맵 크기 입력
			arr = new int[N + 1][N + 1];  // 1-indexed 사용

			// 디저트 카페 숫자 입력
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= N; k++) {
					arr[i][k] = Integer.parseInt(st.nextToken());
				}
			}

			set = new HashSet<>();  // 디저트 종류 중복 체크용
			max = 0;

			// 가능한 시작 위치 탐색 (경계 제외)
			for (int i = 1; i <= N - 2; i++) {
				for (int k = 2; k <= N - 1; k++) {
					back(i, k, i, k, 0, 0);  // (시작y, 시작x, 현재y, 현재x, 방향, 깊이)
				}
			}

			if (max == 0)
				System.out.println("#" + tc + " " + -1);  // 가능한 경로 없을 경우 -1 출력
			else
				System.out.println("#" + tc + " " + max);  // 최대 길이 출력
		}
	}

	// 백트래킹 함수
	public static void back(int y, int x, int Y, int X, int direct, int depth) {
		// 시작 지점으로 돌아온 경우 → 유효한 투어
		if (depth != 0 && y == Y && x == X) {
			max = Math.max(max, depth);  // 최대값 갱신
		} else {
			// 현재 방향으로 한 칸 이동
			int nY = Y + diy[direct];
			int nX = X + dix[direct];

			// 맵 안이고, 아직 먹지 않은 디저트인 경우
			if (1 <= nY && nY <= N && 1 <= nX && nX <= N) {
				if (!set.contains(arr[nY][nX])) {
					set.add(arr[nY][nX]);  // 디저트 먹기
					back(y, x, nY, nX, direct, depth + 1);  // 같은 방향으로 이동 계속
					set.remove(arr[nY][nX]);  // 백트래킹
				}
			}

			// 방향 전환 조건 (한 번은 무조건 방향 바꿔야 하기 때문)
			if (depth == 0) return;

			direct++;  // 다음 방향으로 꺾기
			if (direct >= 4) return;  // 방향 4개 초과 시 종료

			// 꺾은 방향으로 한 칸 이동
			nY = Y + diy[direct];
			nX = X + dix[direct];

			if (1 <= nY && nY <= N && 1 <= nX && nX <= N) {
				if (!set.contains(arr[nY][nX])) {
					set.add(arr[nY][nX]);  // 디저트 먹기
					back(y, x, nY, nX, direct, depth + 1);  // 꺾은 방향으로 계속 탐색
					set.remove(arr[nY][nX]);  // 백트래킹
				}
			}
		}
	}
}