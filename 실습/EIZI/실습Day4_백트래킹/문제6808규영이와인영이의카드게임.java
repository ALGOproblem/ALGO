package 실습.EIZI.실습Day4_백트래킹;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제6808규영이와인영이의카드게임 {
/*
 * 라운드
 * 0. 카드 1~18 적힘. 반 반 나눔.
 * A. 높은 수 카드 : 두 카드 적힌 수 만큼의 점수 얻음
 * B. 낮은 수 카드 & 무승부 : 0
 * 
 * (1) A(규영)이가 뽑은 카드이외의 나머지를 계산하는 함수 defineCard()
 * (2) B(인영)이가 가진 카드의 순열을 계산하는 함수 comb()
 * 		A. SWAP (훨씬 빠름)
 * 		B. 방문체크
 * (3) A(규영)이의 승패를 결정해 WIN과 LOSE를 더하는 함수 game()
 */
	static int[] arrA;
	static int[] arrB;
	static int NUM = 18;
	static int WIN;
	static int LOSE;
	static boolean[] visited;
	static int[] visited_arr;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/문제6808규영이와인영이의카드게임.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			arrA = new int[NUM/2];
			arrB = new int[NUM/2];
			visited = new boolean[NUM/2];
			visited_arr = new int[NUM/2];
			WIN = 0;
			LOSE = 0;
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			for(int i = 0; i<NUM/2; i++) {
				arrA[i] = Integer.parseInt(st.nextToken());
			}
			defineCard();
//			System.out.println(Arrays.toString(arrA));
//			System.out.println(Arrays.toString(arrB));
//			comb_swap(0);
			permutation(0);
			System.out.println("#"+tc+" "+WIN+" "+LOSE);
		}

	}
	static void defineCard() {
		boolean[] visited = new boolean[NUM+1];
		for(int i = 0; i<NUM/2; i++) {
			if (arrA[i]!=0) visited[arrA[i]] = true;
		}
		int idx = 0;
		for(int i = 1; i<=NUM; i++) {
			if (!visited[i]) arrB[idx++] = i;
		}
	}
	static void comb_swap(int idx) {
		//종료
		if(idx==NUM/2) {
//			System.out.println("###"+Arrays.toString(arrB));
			game(arrB); // 다 채워졌으니 반복 시작
			return;
		}
		//재귀
		for(int i = idx; i<NUM/2; i++) {
			swap(idx, i);
			comb_swap(idx+1); // 그 뒤의 경우의 수
			swap(idx, i); // 다시 돌아오기
		}
	}
	static void swap(int idx, int i) {
		int tmp = arrB[idx];
		arrB[idx] = arrB[i];
		arrB[i] = tmp;
	}
	
	static void permutation(int idx) {
		if (idx==NUM/2) {
//			System.out.println(Arrays.toString(visited_arr));
			game(visited_arr);
			return;
		}
		for (int i = 0; i<NUM/2; i++) { // 0부터 모든 visited를 확인
			if (visited[i]) continue; // 방문했을 때는 건너 뛰겠다
			visited_arr[idx] = arrB[i];
			visited[i] = true; // visited[idx]가 아니라 visited[i]이어야 함!
			permutation(idx+1);
			visited[i]= false;
		}
	}
	
	
	
	static void game(int[] arrB) {
		int scoreA = 0;
		int scoreB = 0;
		for(int i = 0; i<NUM/2; i++) {
			if (arrA[i] > arrB[i]) scoreA += arrA[i] + arrB[i];
			else if (arrA[i] < arrB[i]) scoreB += arrA[i] + arrB[i];
		}
		if (scoreA > scoreB) WIN++;
		else if (scoreA < scoreB) LOSE++;
	}
	

}
