package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/* N x N 크기의 미술관에는 검은 벽과 함께 조명이 설치되어 있습니다. 
 * 조명의 빛은 조명이 설치된 칸에서 일직선으로 상하좌우 모든 방향에 있는 칸을 밝혀줄 수 있습니다.
 * 단, 벽을 넘는 곳으로는 빛이 닿을 수 없습니다.
 * 예를 들어 미술관의 형태가 아래와 같다고 해보겠습니다. 검은 칸은 벽, 해 모양이 있는 칸은 조명이 설치된 칸입니다.
 * 
 */

/*
 * 기존 알고리즘
 * 1. '2'인 좌표값을 저장하고 solution(x, y) 진행
 * 2. 해당 x,y안에서 for문으로 상하좌우 4방향 탐색
 * 		A. while(범위 넘지 않음)
 * 		B. if (1만나면 break)
 * 		C. if (0이고 visited==false이면) visited=true바꾸고 answer++
 * 		D. time++ (곱해지는 횟수 증가)
 * 
 * 틀린 이유
 * 1. 먼저 while문 안에서 if문이 끝난 뒤 time을 증가시켜야 하는데 if문 안에서 증가시키는 바람에 무한 반복에 빠짐
 * 2. '조명'도 밝혀지는 부분으로 포함되는데 생각 1도 못함
 * 3. 지금 봤는데 밝혀지지 않은 부분이구나...  문제부터 잘못 봤구나..
 * 4. 찬찬히 다시 보니깐 풀리네... ㅠㅠㅠㅜㅜㅜㅠㅠㅠㅠㅠ
 */

public class 과목평가1미술관의조명 {
	static int N; // N*N
	static int[][] arr;
	static boolean[][] visited;
	static Stack<int[]> lights;
	static int[] dirx = {-1, 1, 0, 0}; // 상하좌우
	static int[] diry = {0, 0, -1, 1};
	static int light;
	static int wall;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test<=T; test++) {
			// 초기화
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N][N];
			lights = new Stack<>();
			light = 0;
			wall = 0;
			// 배열 입력
			for (int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j]==2) {
						lights.add(new int[] {i, j});
						light++;
					}
					if (arr[i][j]==1) wall++;
				}
			}// 입력 완
			while(!lights.isEmpty()) {
				int[] xy = lights.pop();
				findDark(xy[0], xy[1]);
			}
			int answer = N*N-light-wall;
			System.out.println("#"+test+" "+answer);
		}

	}
	static void findDark(int x, int y) {
		for (int i = 0; i<4; i++) {
			int curx = x, cury = y;
			while(curx>=0 && curx<N && cury>=0 && cury<N) {
				if (arr[curx][cury]==1) break; // 1을 만나면 break
				if (arr[curx][cury]==0 && !visited[curx][cury]) {
					light++;
					visited[curx][cury]=true;
				}
				curx += dirx[i];
				cury += diry[i];
			}
		}
	}

}







