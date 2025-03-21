package 공통주차별문제.Week04.APS응용과목평가.MIRIM;
import java.util.Arrays;
import java.util.Scanner;

public class 문제3_건물주고양이 {
	static int N;
	static int[] home;
	static int[][] cats;
	static int[] catOrder;
	static boolean[] selected;
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 고양이 수
			
			home = new int[N];
			for (int i = 0; i < N; i++) {
				home[i] = sc.next().charAt(0) - 'a';
			}// 집 털 입력
			
			cats = new int[N][];
			
			for (int i = 0; i < N; i++) {
				int c = sc.nextInt();
				cats[i] = new int[c];
				
				for (int j = 0; j < c; j++) {
					cats[i][j] = sc.next().charAt(0) - 'a';
				}
				
			}// 고양이 털 입력
			catOrder = new int[N];
			selected = new boolean[N];
			answer = 0;
			perm(0);
			System.out.printf("#%d %d\n", tc, answer);
			
		}//tc
		
	}//main
	
	static void perm(int homeIdx) {
		if (homeIdx == N) {
			answer++;
			//System.out.println(Arrays.toString(catOrder));
			return;
		}
		
		// 고양이 선택
		Loop: for (int cat = 0; cat < N; cat++) {
			if (selected[cat]) continue Loop;
			
			for (int hair : cats[cat]) {
				if (hair == home[homeIdx]) {
					selected[cat] = true;
					catOrder[homeIdx] += cat;
					perm(homeIdx + 1);
					
					// 원상복귀
					selected[cat] = false;
					catOrder[homeIdx] -= cat;
					continue Loop;
				}
			
			}// 털 확인
				
		}// 고양이 확인

	}// 순열
}
