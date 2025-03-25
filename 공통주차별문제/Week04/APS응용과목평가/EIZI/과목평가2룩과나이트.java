package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/* 알고리즘 (백트래킹과 메모이제이션)
 * (1) 종료 : if (time==k) hashset에 위치 문자열로 저장, return;
 * (2) 종료 : if (memo[][][][][]) return;
 * (3) 반복 호출
 * 		A. 룩 이동
 * 			backup하기
 * 			for(4방향)
 * 				while(계속 이동) -> look은 계속 이동할 수 있으니
 * 					if (nx, ny 범위 벗어남? 룩이랑 나이트 겹침?) break; (더 이상 그 방향 이동 불가)
 * 					x, y값 업데이트 및 decideRound(time+1)
 * 					x, y값 다시 백업
 * 		B. 나이트 이동
 * 			backup하기
 * 			for(8방향)
 * 				if (nx, ny 범위 벗어남? 룩이랑 나이트 겹침?) continue; // 이 방향 말고 다른 방향 건너 뛰기
 * 				x,y값 업데이트 및 decideRound(time+1);
 * 				x,y값 다시 백업
 */

/*
 * 틀린 이유
 * 1. 백트래킹이 조금만 복잡해지면 꼬이는 개념 단계
 * 2. 처음에는 순서를 미리 정한 다음 룩과 나이트를 이동시키려고 했는데 어차피 재귀적으로 호출하니깐 하나의 함수에 써도 OK
 * 3. 룩은 while문과 break를 나이트는 continue를 쓴다는 것을 놓침
 */

public class 과목평가2룩과나이트 {
    static int N, M, K;
    static int[][] arr;
    static int[] dirx1 = {-1, 1, 0, 0}; // 룩 : 상, 하, 좌, 우
    static int[] diry1 = {0, 0, -1, 1};
    static int[] dirx2 = {-1, -2, -2, -1, 1, 2, 2, 1}; // 나이트 : 8방향 (체스의 L자 이동)
    static int[] diry2 = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int x1, y1, x2, y2;    // 룩의 위치 (x1, y1), 나이트의 위치 (x2, y2)
    static boolean[][][][][] memo; // 메모이제이션: level, x1, y1, x2, y2 상태가 이미 탐색되었으면 true
    static HashSet<String> hashset; // 최종 상태를 저장 (중복 제거를 위해 문자열로 저장: "x1_y1,x2_y2")
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            arr = new int[N][M];
            // 초기 위치 설정
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) {  // 룩
                        x1 = i;
                        y1 = j;
                    }
                    if (arr[i][j] == 2) {  // 나이트
                        x2 = i;
                        y2 = j;
                    }
                }
            }
            
            // HashSet과 이동 순서 배열 초기화
            hashset = new HashSet<>();
            memo = new boolean[K+1][N][M][N][M];
            
            // 모든 이동 순서(2^K 경우)를 결정한 후에 moveRound()를 호출
            decideRound(0);
            
            // 결과 출력 (테스트케이스 번호와 해시셋에 기록된 최종 상태 개수)
            System.out.println("#" + test + " " + hashset.size());
        }
    }
    
    static void decideRound(int time) {
    	if (time == K) {
    		String s = x1+"_"+y1+"_"+x2+"_"+y2;
    		hashset.add(s);
    		return;
    	}
    	
    	if(memo[time][x1][y1][x2][y2]) return;
        memo[time][x1][y1][x2][y2] = true;
    	
    	int backx1 = x1, backy1 = y1;
    	for (int i = 0; i<4; i++) {
    		int nx = x1, ny = y1;
    		while(true) {
    			nx += dirx1[i];
    			ny += diry1[i];
    			// 종료 조건 : 범위를 벗어나거나 이미 있는 곳에 들어가기
    			if (!isValid(nx, ny) || (nx==x2 && ny==y2)) break;
    			x1 = nx; y1 = ny;
    			decideRound(time+1);
    			x1 = backx1;
    			y1 = backy1;
    		}
    	}
    	int backx2 = x2, backy2 = y2;
    	for (int i = 0; i<8; i++) {
    		int nx = x2, ny = y2;
    		nx += dirx2[i];
			ny += diry2[i];
			if (!isValid(nx, ny) || (nx==x1 && ny==y1)) continue;
			x2 = nx;
			y2 = ny;
			decideRound(time+1);
			x2 = backx2;
			y2 = backy2;
    	}
    }
    
    // 주어진 좌표 (r, c)가 보드 범위 내에 있는지 체크
    static boolean isValid(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c < M);
    }
}
