package 실습.EIZI.실습Day01_부분집합;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* <<문제>>
 * 최대한 비슷한 맛의 음식
 * N개의 식재료. N/2개씩 나누어 두 개의 요리를 하려고 한다
 * A,B 맛의 차이가 최소가 되도록 재료를 배분해야 한다
 * 
 * 1. pick[] 배열과 notpick[] 배열 생성
 * 2. pick[] 배열 sidx==N/2가 되면 나머지 선택되지 않은 것들을 notpick[]에 넣음
 * 3. for 이용해서 점수 계산
 */
public class 문제4012SW대비요리사 {
	static int[][] arr;
	static int[] ingredient;
	static int[] pick;
	static int[] notPick;
	static int N;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/문제4012SW대비요리사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			ingredient = new int[N];
			pick = new int[N/2];
			notPick = new int[N/2];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				StringTokenizer st = new StringTokenizer(s);
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력
			for(int i = 0; i<N; i++) ingredient[i] = i;
			answer = Integer.MAX_VALUE;
			dfs(0,0,0);
			System.out.println("#"+test_case+" "+answer);
		}
	}
	public static void dfs(int idx, int sidx, int notsidx) { // 식재료를 서로 반반으로 나누는 함수
		// 종료 조건 (1) idx==N; arr에서 추가할 더 이상의 요소 없음 (2) sidx==N/2; ingredient에 더 이상 자리가 없음
		if (idx==N) return;
		if (sidx==N/2) {
			// 맛의 차이를 계산하는 함수 호출
			fillNotPick();
//			System.out.println("선택O"+Arrays.toString(pick));
//			System.out.println("선택X"+Arrays.toString(notPick));
			step();
			return;
		}
		pick[sidx] = ingredient[idx];
		dfs(idx+1, sidx+1, notsidx); // 포함 O
		dfs(idx+1, sidx, notsidx+1); // 포함 X
	}
    public static void fillNotPick() { // 🔹 B 그룹을 자동으로 채우는 함수
        boolean[] isPicked = new boolean[N];
        for (int num : pick) isPicked[num] = true;

        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (!isPicked[i]) notPick[idx++] = i; // 🔹 A에 포함되지 않은 나머지를 B 그룹에 자동 배정
        }
    }
	
	public static void step() {
		// 2. 나눠진 재료의 시너지를 계산하기
		int sumSelect = 0;
		int sumNotSelect = 0;
        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) { // 🔹 범위 수정
                sumSelect += arr[pick[i]][pick[j]] + arr[pick[j]][pick[i]];
                sumNotSelect += arr[notPick[i]][notPick[j]] + arr[notPick[j]][notPick[i]];
            }
        }
//		if (Math.abs(sumSelect - sumNotSelect)<100)  System.out.println("차이계산"+Math.abs(sumSelect - sumNotSelect));
		answer = Math.min(answer, Math.abs(sumSelect - sumNotSelect));
	}

}
