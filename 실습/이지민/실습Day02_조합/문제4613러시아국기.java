package 실습.이지민.실습Day02_조합;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/* 브루트포스 + 조합 방식
 * 1. 흰 줄의 경계(A)와 파란 줄의 경계(B)를 설정하녀 빨간 줄의 범위(C)는 자동으로 계산된다
 * 2. A는 0부터 N-3까지의 범위의 값을 가질 수 있고 B는 A+1부터 N-2까지의 범위를 가질 수 있다
 * 3. ABC의 조합을 먼저 계산한 다음, 각 행에서 countChages 함수를 통해 몇 칸이 바뀌는지 계산한다
 * 
 * 브루트포스 + DP(Dynamic Programming) 최적화
 * 1. 위의 코드와 거의 유사하나, 변경해야 하는 색깔의 수를 DP 테이블에 미리 저장하여 중복 계산을 줄인다
 * 2. 각 행을 흰(W), 파(B), 빨(R)로 칠하는 비용을 미리 구한 후 dp[i][j]에 특정행까지 특정 색으로 칠했을 때의 최소 비용을 저장
 * 
 * 백트레킹 (최악 N^3 -> 오래 걸림)
 * 1. wEnd, bEnd를 통해 흰색 구역과 파란색 구역의 범위를 미리 설정하고 baktrackingSolution을 호출
 * 2. backtrackingSolution(row, wEnd, bEnd, cost);
 * 3. 종료 조건 : (모든 행을 다 칠한 경우 row == N) min값을 업데이트하고 return;
 * 4. 반복 조건 : 현재 행(row)의 범위가 wEnd 안에 있는지, bEnd 안에 있는지, 그 이상인지 고려한 다음 (row+1, wENd, bEnd, cost+wCost/bCost/rCost)
 * 
 */

public class 문제4613러시아국기 {

	static char[][] arr;
	static int N;
	static int M;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/문제4613러시아국기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			N = Integer.parseInt(st.nextToken()); // 3<=N<=50 (행)
			M = Integer.parseInt(st.nextToken()); // 3<=M<=50 (열)
			arr = new char[N][M];
			answer = N*M;
			for (int i = 0; i<N; i++) {
				s = br.readLine();
				for (int j = 0; j<M; j++) {
					arr[i][j] = s.charAt(j);
				}
			}// 입력 받기
//			comb();
			System.out.print("#"+test_case+" ");
//			dpSolution();
			solveBacktracking();
//			System.out.println(" ---------- ");
			
			

		}

	}
	//////////////// bruteforce + 조합 방식/////////////////////////
	public static void comb() {
		for (int a =0; a<=N-3; a++) {
			for (int b = a+1; b<=N-2; b++) {
				answer = Math.min(answer, countChanges(a, b));
			}
		}
	}
	public static int countChanges(int a, int b) {
		int count = 0;
		for (int i = 0; i<=a; i++) {
			for (int j = 0; j<M; j++) {
				if (arr[i][j]!= 'W') count++;
			}
		}
		for (int i = a+1; i<=b; i++) {
			for (int j = 0; j<M; j++) {
				if (arr[i][j]!= 'B') count++;
			}
		}
		for (int i = b+1; i<N; i++) {
			for (int j = 0; j<M; j++) {
				if (arr[i][j]!= 'R') count++;
			}
		}
		return count;
	}
	//////////////// bruteforce + DP 방식/////////////////////////
	public static void dpSolution() {
		int[][] cost = new int[N][3]; // cost[i][0] = W, cost[i][1] = B, cost[i][2] = R
		for (int i= 0; i<N; i++) {
			for (int j = 0; j<M; j++) {
				if (arr[i][j]!='W') cost[i][0]++;
				if (arr[i][j]!='B') cost[i][1]++;
				if (arr[i][j]!='R') cost[i][2]++;
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int a = 0; a<=N-3; a++) {
			for (int b = 0; b<=N-2; b++) {
				int sum = 0;
				for (int i = 0; i<=a; i++) sum += cost[i][0];
				for (int i = a+1; i<=b; i++) sum += cost[i][1];
				for (int i = b+1; i<=N-1; i++) sum+= cost[i][2];
				answer = Math.min(sum, answer);
			}
		}
		System.out.println(answer);
	}
	///////////////////////////
	static int minCost;

	public static void backtrackingSolution(int row, int wEnd, int bEnd, int cost) {
	    if (bEnd >= N - 1) return; // 파란색이 끝나는 곳이 마지막 줄보다 크면 안됨
	    if (row == N) { // 기저 조건: 모든 행을 다 칠했을 때
	        minCost = Math.min(minCost, cost); // 최소 비용 갱신
	        return;
	    }

	    int wCost = 0, bCost = 0, rCost = 0;
	    for (int j = 0; j < M; j++) {
	        if (arr[row][j] != 'W') wCost++; // W로 변경해야 하는 비용
	        if (arr[row][j] != 'B') bCost++; // B로 변경해야 하는 비용
	        if (arr[row][j] != 'R') rCost++; // R로 변경해야 하는 비용
	    }

	    if (row <= wEnd) { // 흰색 영역 선택
	        backtrackingSolution(row + 1, wEnd, bEnd, cost + wCost);
	    } else if (row <= bEnd) { // 파란색 영역 선택
	        backtrackingSolution(row + 1, wEnd, bEnd, cost + bCost);
	    } else { // 빨간색 영역 선택
	        backtrackingSolution(row + 1, wEnd, bEnd, cost + rCost);
	    }
	}

	public static void solveBacktracking() {
		minCost = Integer.MAX_VALUE;
	    for (int wEnd = 0; wEnd < N - 2; wEnd++) { // 흰색 마지막 줄 (최소 1줄)
	        for (int bEnd = wEnd + 1; bEnd < N - 1; bEnd++) { // 파란색 마지막 줄 (최소 1줄)
	            backtrackingSolution(0, wEnd, bEnd, 0);
	        }
	    }
	    System.out.println(minCost);
	}







}
