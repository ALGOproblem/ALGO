import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA7733_치즈도둑 {
	static int[][] arr;  // 치즈 상태를 저장할 맵
	static int N;  // 맵의 크기 (N x N)
	static Map<Integer, List<Integer>> map;  // 치즈 높이별 위치들을 저장할 맵
	static int max;  // 최대로 나올 수 있는 덩어리 개수
	static int[] diy = {0, 0, 1, -1};  // 4방향 y축 이동 (우좌하상)
	static int[] dix = {1, -1, 0, 0};  // 4방향 x축 이동
	static boolean[][] visit;  // 방문 여부 저장 배열

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트케이스 수

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());  // 맵 크기 입력

			arr = new int[N + 1][N + 1];  // 1-based index로 선언
			map = new HashMap<>();  // 치즈 높이별 좌표 저장할 맵 초기화

			// 맵 입력 및 map에 치즈 높이별 좌표 기록
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= N; k++) {
					arr[i][k] = Integer.parseInt(st.nextToken());
					int height = arr[i][k];  // 현재 좌표의 치즈 높이
					int key = i * 1000 + k;  // y, x 좌표를 하나의 정수로 표현 (복원 시 y=key/1000, x=key%1000)

					if (!map.containsKey(height)) {
						List<Integer> list = new ArrayList<>();
						list.add(key);
						map.put(height, list);  // 해당 높이 처음 등장 시 새 리스트 생성
					} else {
						map.get(height).add(key);  // 이미 있으면 리스트에 추가
					}
				}
			}

			max = 1;  // 최소 덩어리 수는 1부터 시작

			// 1일부터 100일까지 시뮬레이션 (치즈 녹는 날)
			for (int i = 1; i <= 100; i++) {
				if (map.containsKey(i)) {
					// 해당 날짜에 녹는 치즈 위치들을 -1로 처리
					for (int a : map.get(i)) {
						int y = a / 1000;
						int x = a % 1000;
						arr[y][x] = -1;  // 해당 위치의 치즈가 녹음
					}

					visit = new boolean[N + 1][N + 1];  // 방문 배열 초기화
					int sum = 0;  // 덩어리 개수

					// BFS로 덩어리 탐색
					for (int k = 1; k <= N; k++) {
						for (int q = 1; q <= N; q++) {
							if (!visit[k][q] && arr[k][q] != -1) {  // 아직 방문 안 했고 치즈가 남아있다면
								sum += bfs(k, q);  // 새로운 덩어리 발견
							}
						}
					}

					max = Math.max(max, sum);  // 최대 덩어리 수 갱신
				}
				else {
					continue;  // 해당 높이 치즈가 없으면 생략
				}
			}

			System.out.println("#" + tc + " " + max);  // 결과 출력
		}
	}

	// BFS를 이용해 하나의 치즈 덩어리를 탐색
	public static int bfs(int y, int x) {
		Queue<int[]> qu = new LinkedList<>();
		qu.offer(new int[] { y, x });
		visit[y][x] = true;  // 시작 위치 방문 처리

		while (!qu.isEmpty()) {
			int[] now = qu.poll();

			for (int i = 0; i < 4; i++) {
				int Y = now[0] + diy[i];
				int X = now[1] + dix[i];

				// 맵 범위 체크
				if (1 <= Y && Y <= N && 1 <= X && X <= N) {
					if (arr[Y][X] == -1) continue;  // 녹은 칸은 스킵

					if (!visit[Y][X]) {
						visit[Y][X] = true;  // 방문 처리
						qu.offer(new int[] { Y, X });  // 다음 탐색 좌표 추가
					}
				}
			}
		}

		return 1;  // 덩어리 1개 탐색 완료
	}
}
