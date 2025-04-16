
// 문제 조건 생각만 하다가 정작 구현해서 틀려서 다시 품
// 1. 청소 완료 칸은 '1'이 아닌 (1은 벽) '2'로 표시
// 2. 종료 조건은 (칸을 벗어남) + ('1')일 때

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14503로봇청소기 {
	static int[][] arr;
	static int[] dirx = {-1, 0, 1, 0}; // 상우하좌
	static int[] diry = {0, 1, 0, -1};
	// 후진의 경우에는 dirx[(x+2)%4]
	static int R, C; // 방의 길이
	static int sx, sy, sdir; // 로봇청소기 좌표값 및 방향
	static int curx, cury, curdir;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄에는 R X C
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st2.nextToken()); // 로봇청소기 시작 좌표
		sy = Integer.parseInt(st2.nextToken());
		sdir = Integer.parseInt(st2.nextToken()); // 로봇청소기 바라보는 방향 (0: 상, 1: 우, 2:하, 3:좌)
		for (int i = 0; i<R; i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());
			for (int j = 0; j<C; j++) {
				arr[i][j] = Integer.parseInt(st3.nextToken());
			}
		}// arr입력
		///////////////////////////////////////////////////////////////////////////////
		curx = sx;
		cury = sy;
		curdir = sdir;
		boolean isFinish = false;
		while(!isFinish) {
			isFinish = !(cleanRoom());
//			System.out.println(ans);
		}
		System.out.println(ans);
	}
	static boolean cleanRoom() {
		if (arr[curx][cury]==0) { // 청소해야해요..
			arr[curx][cury]=2; // 벽과 헷갈리지 않도록
			ans++;
//			System.out.println("청소 완료" + ans);
			return true;
		}
		if (is4dirDirty(curx, cury)!=null) {
			//반 시계방향으로 90도 회전
			if (curdir==0) {
				curdir = 3;
			} else {
				curdir = curdir-1;
			}
//			System.out.println("회전");
			int rx = curx + dirx[curdir];
			int ry = cury + diry[curdir];
			//앞쪽칸이 청소되지 않았다면 전진
			if (rx>=0 && ry>=0 && rx<R && ry<C && arr[rx][ry]==0) {
				curx = rx;
				cury = ry;
			}
			return true;
		}
		// 한 칸 후진 가능? 후진
		int backx = curx+dirx[(curdir+2)%4];
		int backy = cury+diry[(curdir+2)%4];
		if (backx >=0 && backy >= 0 && backx <R && backy<C && arr[backx][backy]!=1) {
			curx = backx;
			cury = backy;
			return true;
		}
		return false;
	}
	static int[] is4dirDirty(int curx, int cury) { // 4방향 탐색 완
		for (int i = 0; i<4; i++) {
			int nx = curx+dirx[i];
			int ny = cury+diry[i];
			if (nx <0 || ny < 0 || nx >= R || ny >= C) continue;
			if (arr[nx][ny]==0) {
				int[] dirty = new int[] {nx, ny};
				return dirty;
			}
		}
		return null;
	}

}
