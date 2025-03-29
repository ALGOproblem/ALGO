import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA1868_파핑파핑지뢰찾기 {
	static int N;  // 지도의 크기 (N x N)
	static char[][] arr;  // 지뢰 맵 정보 ('.': 빈칸, '*': 지뢰)
	static boolean[][] visit;  // 방문 여부 체크
	static int[] diy= {-1,-1,0,1,1,1,0,-1};  // 8방향 y축 이동
	static int[] dix= {0,1,1,1,0,-1,-1,-1};  // 8방향 x축 이동

	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트케이스 수 입력

		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());  // 지도 크기 입력
			arr = new char[N][N];

			// 지뢰맵 입력
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				for(int k=0; k<N; k++) {
					arr[i][k] = str.charAt(k);
				}
			}

			visit = new boolean[N][N];  // 방문 배열 초기화
			int sum = 0;  // 클릭 횟수 저장

			// 1차 순회: 빈칸 중 주변에 지뢰가 없는 지점을 찾아 BFS 시작
			for(int i=0; i<N; i++) {
				for(int k=0; k<N; k++) {
					if(arr[i][k]=='.' && !visit[i][k]) {  // 아직 방문 안 한 빈칸이면
						if(!find(i,k))  // 주변에 지뢰가 없는 경우
							sum += bfs(i,k);  // BFS로 연쇄 클릭, 클릭 횟수 +1
					}
				}
			}

			// 2차 순회: 아직 클릭되지 않은 나머지 빈칸은 클릭 1번 필요
			for(int i=0; i<N; i++) {
				for(int k=0; k<N; k++) {
					if(!visit[i][k] && arr[i][k]=='.')
						sum++;
				}
			}

			System.out.println("#"+tc+" "+sum);  // 결과 출력
		}
	}

	// 해당 좌표 주변에 지뢰(*)가 있는지 확인
	public static boolean find(int y, int x) {
		boolean bomb = false;
		for(int i=0; i<8; i++) {
			int Y = y + diy[i];
			int X = x + dix[i];
			if(Y >= 0 && Y < N && X >= 0 && X < N) {  // 맵 범위 체크
				if(arr[Y][X] == '*') {
					bomb = true;  // 주변에 지뢰가 하나라도 있으면 true
				}
			}
		}
		return bomb;
	}

	// BFS를 이용한 연쇄 빈칸 열기
	public static int bfs(int y, int x) {
		Queue<int[]> qu = new LinkedList<>();
		qu.offer(new int[] {y, x});  // 시작 좌표 큐에 삽입
		visit[y][x] = true;  // 방문 처리

		while(!qu.isEmpty()) {
			int[] now = qu.poll();  // 현재 좌표 꺼냄

			for(int i=0; i<8; i++) {  // 8방향 체크
				int Y = now[0] + diy[i];
				int X = now[1] + dix[i];
				if(Y >= 0 && Y < N && X >= 0 && X < N) {
					if(!visit[Y][X]) {
						visit[Y][X] = true;
						// 만약 인접한 칸이 빈칸이고, 그 주변에도 지뢰가 없다면 → 큐에 추가
						if(arr[Y][X] == '.' && !find(Y,X)) {
							qu.offer(new int[] {Y, X});
						}
					}
				}
			}
		}
		return 1;  // 클릭 1번으로 연쇄 열기 완료
	}
}