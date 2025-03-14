package 실습.이지민.실습Day01_부분집합;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제5215햄버거다이어트 {
	/*
	 * 햄버거의 맛은 최대한 유지하면서 정해진 칼로리를 넘지 않는 햄버거를 주문하여 먹으려고 한다.
	 * (단 여러 재료를 조합하였을 햄버거의 선호도는 조합된 재료들의 맛에 대한 점수의 합으로 결정되고, 같은 재료를 여러 번 사용할 수 없으며, 햄버거의 조합의 제한은 칼로리를 제외하고는 없다.)
	 */
	
	
	/*
	 * 비트 마스킹 (부분집합 완전 탐색)을 이용한 방법
	 * 1. 모든 집합을 전부 탐색할 예정 (int i = 0; i<(1<<N); i++) -> i < Math.pow(2, N)
	 * 2. i를 2진수처럼 생각하고 각 자릿수마다 1이면 해당 요소가 존재, 0이면 없음으로 생각하자
	 * 		0 = 0 0 0 (---)
	 * 		1 = 0 0 1 (--C)
	 * 		2 = 0 1 0 (-B-)
	 * 		3 = 0 1 1 (-BC) 
	 * 		4 = 1 0 0 (A--)
	 * 		5 = 1 0 1 (A-C)
	 * 		6 = 1 1 0 (AB-)
	 * 		7 = 1 1 1 (ABC)
	 * 3. j를 움직이면서 (100) <- (010) <- (001) 해당 요소와 & 를 진행해서 1이라면 행동 개시
	 * 	  for (int j = 1; j = N; j++)
	 * 		  if ((i& 1<<j))!=0)
	 * 
	 * 
	 * DFS(깊이 우선 탐색) + 백트래킹
	 * 1. 종료 조건 : 칼로리 초과 시 종료 (sumKcal > L)
	 *              모든 재료를 확인했을 때 (idx == N)
	 * 2. 현재 재료를 선택하는 경우 dfs(idx+1, sumScore+scores[idx], sumKcal + kcals[idx])
	 *    현재 재료를 선택하지 않는 경우 dfs (idx+1, sumScore, sumKcal)
	 * 
	 * 
	 * ＤＰ　이용　：　이전에　계산한　값을　다시　계산하지　않고　바로　사용하기　위한　배열
	 * 1. int[][]dp = new int[N+1][L+1]; // N개의 재료를 사용해서 L칼로리 이하로 만들 수 있는 최대 점수
	 * 2. 현재 재료를 선택하지 않는 경우 : dp[i][w] = dp[i-1][w]
	 * 	  현재 재료를 선택하는 경우 : dp[i-1][w-kcals[i-1]] + scores[i-1]; // 현재 재료의 칼로리 kcals[i-1]을 뺀 뒤 추가
	 * 
			각 값은 **현재까지 고려한 재료 수 (`i`)와 현재 칼로리 제한 (`w`)에서 얻을 수 있는 최대 점수**를 의미해!  

			| 재료 개수 (i) | 0 kcal | 100 kcal | 200 kcal | 300 kcal | 400 kcal | 500 kcal | 600 kcal | 700 kcal | 800 kcal | 900 kcal | 1000 kcal |
			|--------------|--------|----------|----------|----------|----------|----------|----------|----------|----------|----------|-----------|
			| **Item 0** (X) | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
			| **Item 1** (100점, 200kcal) | 0 | 0 | 100 | 100 | 100 | 100 | 100 | 100 | 100 | 100 | 100 |
			| **Item 2** (300점, 500kcal) | 0 | 0 | 100 | 100 | 100 | 300 | 300 | 300 | 300 | 300 | 400 |
			| **Item 3** (250점, 300kcal) | 0 | 0 | 100 | 250 | 250 | 350 | 350 | 550 | 550 | 550 | 650 |
			| **Item 4** (500점, 1000kcal) | 0 | 0 | 100 | 250 | 250 | 350 | 350 | 550 | 550 | 550 | 750 |
			| **Item 5** (400점, 400kcal) | 0 | 0 | 100 | 250 | 400 | 450 | 650 | 650 | 750 | 750 | 750 |
	 * 
	 */
	static int test_case;
	static int[][] arr;
	static int N;
	static int L;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/실습Day01_부분집합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (test_case = 1; test_case<=T; test_case++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			N = Integer.parseInt(st.nextToken()); // 1<=N<=20 선택할 수 있는 자연수
			L = Integer.parseInt(st.nextToken()); // 1<=L<=10000 제한하는 칼로리수
			arr = new int[N][2];
			for (int i = 0; i < N; i++) {
				s = br.readLine();
				st = new StringTokenizer(s);
				int score = Integer.parseInt(st.nextToken()); // i번째 재료에 대한 평가
				int kcal = Integer.parseInt(st.nextToken()); // i번째 재료에 대한 칼로리
				arr[i][0] = score;
				arr[i][1] = kcal;
			} // 입력 받기
//			bitmasking();
			dfsSolution();
		}

	}
	public static void bitmasking() {
		int max_score = 0;
		for (int i = 0; i < (1 << N); i++) { // 부분집합의 수
			int kcal = 0;
			int score = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) !=0) {
					score += arr[j][0];
					kcal += arr[j][1];
				}
			}// j
			if (kcal <= L) max_score = Math.max(score, max_score);
		}// i
		System.out.println("#"+test_case+" "+max_score);
	}
	public static void dfs(int idx, int kcal, int score) {
		if (kcal>L) return;
		if (idx==N) {
			answer = Math.max(answer, score);
			return;
		}
		dfs(idx+1, kcal + arr[idx][1], score + arr[idx][0]); // 더함
		dfs(idx+1, kcal, score); // 더하지 않음
	}
	public static void dfsSolution() {
		answer = -1;
		dfs(0, 0, 0);
		System.out.println("#"+test_case+" "+answer);
	}
	public static void dpSolution() {
	    int[][] dp = new int[N + 1][L + 1];

	    for (int i = 1; i <= N; i++) {
	        for (int w = 0; w <= L; w++) {
	            if (arr[i - 1][1] <= w) { // 현재 재료가 w 칼로리 제한 내에 포함될 수 있는가?
	                dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - arr[i - 1][1]] + arr[i - 1][0]);
	            } else { // 재료를 추가할 수 없는 경우
	                dp[i][w] = dp[i - 1][w];
	            }
	        }
	    }
	    
	    System.out.println(dp[N][L]);
	}


}
