package 실습.EIZI.실습Day01_부분집합;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 문제5215햄버거다이어트2 {
	/*
	 * 햄버거의 맛은 최대한 유지하면서 정해진 칼로리를 넘지 않는 햄버거를 주문하여 먹으려고 한다.
	 * (단 여러 재료를 조합하였을 햄버거의 선호도는 조합된 재료들의 맛에 대한 점수의 합으로 결정되고, 같은 재료를 여러 번 사용할 수 없으며, 햄버거의 조합의 제한은 칼로리를 제외하고는 없다.)
	 */
	
	static int N, L; // N : 재료의 수, L : 제한 칼로리
	static int[] cals;
	static int[] scores;
	static int ans;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/실습Day01_부분집합.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc<=T; tc++) {
			N = sc.nextInt();
			L = sc.nextInt();
			scores = new int[N];
			cals = new int[N];
			for (int i = 0; i<N; i++) {
				scores[i] = sc.nextInt();
				cals[i] = sc.nextInt();
			}//input끝
			ans = 0;
			make(0,0,0);
			System.out.println("#"+tc+" "+ans);
			
		}//tc
	}//main
	//1.비트마스킹
	//2.재귀함수 부분집합
	//3.백트래킹 : 모든 경우의 수를 보지 않을 것이다. (유망성 검사: kcal 들고 다니면서 검사)

	static void make(int idx, int sumCal, int sumScore) {
	    if (sumCal > L) return; // 제한 칼로리를 초과하면 종료

	    if (idx == N) { // 모든 재료를 다 고려했을 때
	        ans = Math.max(ans, sumScore); // 최댓값 업데이트
	        return;
	    }
	    //재료를 선택하지 않는 경우를 먼저 호출하여, 배열 접근 전에 idx == N을 검사할 수 있도록 수정
	    make(idx + 1, sumCal + cals[idx], sumScore + scores[idx]); // 현재 재료를 선택하는 경우
	    make(idx + 1, sumCal, sumScore); // 현재 재료를 선택하지 않는 경우
	}



}














