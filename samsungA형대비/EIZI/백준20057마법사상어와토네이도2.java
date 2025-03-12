package week3;

import java.util.*;
import java.io.*;

/* (+) 오랜만에 푸니깐 너무 새록새록하다
 * 1. 달팽이 순회 응용
 * 2. direction이 좌, 하, 우, 상 (서, 남, 동, 북)일 때 모든 ratio 관련 좌표 구함
 * 3. 그냥 좌표값 90도회전, 180도회전, 270도회전 구하는 문제였는데 너무 어렵게 생각했다
 * 
 */

public class 백준20057마법사상어와토네이도2 {
	static int[] dirx = { 0, 1, 0, -1 }; // 서, 남, 동, 북
	static int[] diry = { -1, 0, 1, 0 };
	static int[][] windx = { // 총 9개 (a포함)
		    {-1, 1, -1, 1, -1, 1, -2, 2, 0}, // 서
		    {-1, -1, 0, 0, 1, 1, 0, 0, 2},  // 남 (여기 마지막 좌표값 틀림)
		    {-1, 1, -1, 1, -1, 1, -2, 2, 0}, // 동
		    {1, 1, 0, 0, -1, -1, 0, 0, -2} // 북
    };
	static int[][] windy = {
		    {1, 1, 0, 0, -1, -1, 0, 0, -2},  // 서
		    {-1, 1, -1, 1, -1, 1, -2, 2, 0},  // 남
		    {-1, -1, 0, 0, 1, 1, 0, 0, 2},    // 동 (여기 마지막 좌표값 틀림)
		    {1, -1, 1, -1, 1, -1, 2, -2, 0}   // 북
    };
	static int[] ratio = {1, 1, 7, 7, 10, 10, 2, 2, 5};
	static int[][] arr;
	static int N;
	static int answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/백준20057마법사상어와토네이도.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		answer = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // arr입력
		snail();
		System.out.println(answer);
	}

	static void snail() {
		int x = N / 2, y = N / 2;
		int num = 1;
		int index = 1;
		int direction = 0;
		while (num < N * N) {
			// 2개 방향 지난 이후 전환
			for (int d = 0; d < 2; d++) {
				// index만큼 방향 이동
				for (int i = 0; i < index; i++) {
					if (x + dirx[direction] >= 0 && x + dirx[direction] < N && y + diry[direction] >= 0 && y + diry[direction] < N) {
						x += dirx[direction];
						y += diry[direction];
						num++;
						
						if (arr[x][y] <= 0) continue; // 불
						spreadWind(x, y, direction);
					}
				}
				if (x == 0 && y == 0) return; // ✅ (0,0)에 도달하면 즉시 종료

				// direction 방향 바꾸기
				direction = (direction + 1) % 4;
			}
			index++; // index 증가
		}
	}// snail

	static void spreadWind(int x, int y, int direction) {
	    int totalSand = arr[x][y]; // 현재 위치 모래량
	    arr[x][y] = 0; // 현재 위치 모래 제거
	    int movedSand = 0; // 퍼진 모래 총합

	    // 퍼지는 모래 처리 (9개 방향)
	    for (int i = 0; i < 9; i++) { 
	        int nx = x + windx[direction][i]; // 새로운 x 위치
	        int ny = y + windy[direction][i]; // 새로운 y 위치
	        int sand = (totalSand * ratio[i]) / 100; // 퍼질 모래량

	        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
	            arr[nx][ny] += sand; // 격자 내부면 더하기
	        } else {
	            answer += sand; // 격자 밖이면 answer에 추가
	        }
	        movedSand += sand; // 퍼진 모래 합산
	    }

	    // α 위치 (남은 모래 처리)
	    int alphaX = x + dirx[direction]; // α 위치 (토네이도 진행 방향)
	    int alphaY = y + diry[direction];
	    int alphaSand = totalSand - movedSand; // 남은 모래 계산

	    if (alphaX >= 0 && alphaX < N && alphaY >= 0 && alphaY < N) {
	        arr[alphaX][alphaY] += alphaSand; // α 위치에 남은 모래 추가
	    } else {
	        answer += alphaSand; // 격자 밖이면 answer에 추가
	    }
//	    System.out.println("totalSand: " + totalSand + ", movedSand: " + movedSand + ", alphaSand: " + alphaSand);
	}


}
