

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953_탈주범검거 {
	static int N;  // 세로 길이
	static int M;  // 가로 길이
	static int R;  // 시작 지점 행
	static int C;  // 시작 지점 열
	static int L;  // 탈출 가능한 시간 제한
	static int[][] arr;  // 지하 터널 구조 정보
	static boolean[][] visit;  // 방문 여부 확인 배열
	static int[] diy = {-1, 0, 1, 0};  // 상우하좌 방향의 y 변화
	static int[] dix = {0, 1, 0, -1};  // 상우하좌 방향의 x 변화
	static int ans;  // 이동 가능한 위치 수
	static Queue<int[]> qu;  // BFS용 큐 (좌표 + 현재 시간)

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // 세로 크기
			M = Integer.parseInt(st.nextToken());  // 가로 크기
			R = Integer.parseInt(st.nextToken());  // 시작 위치 y
			C = Integer.parseInt(st.nextToken());  // 시작 위치 x
			L = Integer.parseInt(st.nextToken());  // 시간 제한

			ans = 1;  // 시작 위치 포함하므로 1부터 시작
			arr = new int[N][M];  // 맵 초기화

			// 지하 터널 구조 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					arr[i][k] = Integer.parseInt(st.nextToken());
				}
			}

			visit = new boolean[N][M];  // 방문 배열 초기화
			bfs();  // BFS 시작

			System.out.println("#" + tc + " " + ans);  // 결과 출력
		}
	}

	// BFS를 이용해 시간 내 이동 가능한 위치 탐색
	public static void bfs() {
		qu = new LinkedList<>();
		visit[R][C] = true;  // 시작 지점 방문 처리
		qu.offer(new int[] {R, C, 1});  // {y, x, 현재 시간}

		while (!qu.isEmpty()) {
			int[] now = qu.poll();

			int line = arr[now[0]][now[1]];  // 현재 위치의 터널 구조 번호
			move(line, now[0], now[1], now[2] + 1);  // 가능한 방향으로 이동 시도
		}
	}

	// 현재 위치의 터널 타입에 따라 가능한 방향으로 이동
	public static void move(int now, int y, int x, int sum) {
		if (sum > L) return;  // 시간 초과 시 이동 중단

		// 터널 타입 1: 상하좌우 가능
		if (now == 1) {
			for (int i = 0; i < 4; i++) {
				int Y = y + diy[i];
				int X = x + dix[i];
				insert(i, Y, X, sum);  // 각 방향으로 이동 시도
			}
		}
		// 터널 타입 2: 상하만 가능
		else if (now == 2) {
			int Y = y + diy[0];
			int X = x + dix[0];
			insert(0, Y, X, sum);

			Y = y + diy[2];
			X = x + dix[2];
			insert(2, Y, X, sum);
		}
		// 터널 타입 3: 좌우만 가능
		else if (now == 3) {
			int Y = y + diy[1];
			int X = x + dix[1];
			insert(1, Y, X, sum);

			Y = y + diy[3];
			X = x + dix[3];
			insert(3, Y, X, sum);
		}
		// 터널 타입 4: 상우 가능
		else if (now == 4) {
			int Y = y + diy[0];
			int X = x + dix[0];
			insert(0, Y, X, sum);

			Y = y + diy[1];
			X = x + dix[1];
			insert(1, Y, X, sum);
		}
		// 터널 타입 5: 우하 가능
		else if (now == 5) {
			int Y = y + diy[1];
			int X = x + dix[1];
			insert(1, Y, X, sum);

			Y = y + diy[2];
			X = x + dix[2];
			insert(2, Y, X, sum);
		}
		// 터널 타입 6: 하좌 가능
		else if (now == 6) {
			int Y = y + diy[2];
			int X = x + dix[2];
			insert(2, Y, X, sum);

			Y = y + diy[3];
			X = x + dix[3];
			insert(3, Y, X, sum);
		}
		// 터널 타입 7: 좌상 가능
		else if (now == 7) {
			int Y = y + diy[3];
			int X = x + dix[3];
			insert(3, Y, X, sum);

			Y = y + diy[0];
			X = x + dix[0];
			insert(0, Y, X, sum);
		}
	}

	// 이동하려는 방향으로 실제 갈 수 있는지 판단 후 큐에 삽입
	public static void insert(int direct, int Y, int X, int sum) {
		if (Y >= 0 && Y < N && X >= 0 && X < M) {  // 범위 체크
			if (arr[Y][X] == 0) return;  // 빈 공간은 이동 불가

			// 연결 가능하고, 아직 방문하지 않았다면 이동
			if (check(direct, Y, X) && !visit[Y][X]) {
				qu.offer(new int[] {Y, X, sum});  // 이동한 좌표와 시간 큐에 삽입
				visit[Y][X] = true;  // 방문 처리
				ans++;  // 이동 가능 칸 수 증가
			}
		}
	}

	// 현재 방향으로 이동한 위치가 연결 가능한 터널 구조인지 확인
	public static boolean check(int direct, int Y, int X) {
		if (arr[Y][X] == 1) return true;  // 모든 방향 가능

		if (direct == 0) {  // 북쪽에서 이동해 온 경우
			if (arr[Y][X] == 2 || arr[Y][X] == 5 || arr[Y][X] == 6) return true;
		}
		else if (direct == 1) {  // 동쪽에서 이동해 온 경우
			if (arr[Y][X] == 3 || arr[Y][X] == 6 || arr[Y][X] == 7) return true;
		}
		else if (direct == 2) {  // 남쪽에서 이동해 온 경우
			if (arr[Y][X] == 2 || arr[Y][X] == 4 || arr[Y][X] == 7) return true;
		}
		else if (direct == 3) {  // 서쪽에서 이동해 온 경우
			if (arr[Y][X] == 3 || arr[Y][X] == 4 || arr[Y][X] == 5) return true;
		}

		return false;  // 연결 불가
	}
}
