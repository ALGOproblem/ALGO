package 공통주차별문제.Week04.APS응용과목평가.MIRIM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 문제1_미술관의조명 {
    static int N;
    static int[][] map; // 미술관
    static List<int[]> light; // 조명의 위치 정보
    static int[] dr = { 1, -1, 0, 0}; // 상하좌우 행
    static int[] dc = {0, 0, -1, 1}; //상하좌우 열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();

            map = new int[N][N];
            light = new ArrayList<int[]>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();

                    if (map[i][j] == 2) {
                        light.add(new int[] {i, j});
                    }
                }
            } // 미술관 형태 입력

            // 조명 위치마다 turnOn 실행
            for (int[] l : light) {
                turnOn(0, l[0], l[1]);
            }

            int answer = 0; // 밝혀지지 않은 칸의 개수
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0)
                        answer++;
                }
            }

            System.out.printf("#%d %d\n", tc, answer);

        }// tc
        sc.close();
    }// main

    // r, c: 조명의 위치 입력 받음
    // idx와 delta 개념을 통해 조명 켜줌
    static void turnOn(int idx, int r, int c) {
        if (idx == 4) return; // 종료조건: 상하좌우 모두 확인 시 반환

        // 상하좌우로 이동할 위치
        int nr = r + dr[idx];
        int nc = c + dc[idx];

        // 다음 위치가 map 내부에 있을 때만
        while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
            if (map[nr][nc] == 1) break; // 벽과 만나면 멈춘다

            map[nr][nc] = 2; // 밝혀준다

            nr += dr[idx]; // 다음 위치로 이동
            nc += dc[idx];

        }

        turnOn(idx + 1, r, c); // 다른 방향 확인

    }// turnOn

}
