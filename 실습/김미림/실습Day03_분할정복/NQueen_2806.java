import java.util.Arrays;
import java.util.Scanner;

public class NQueen_2806 {
	// 경우의 수 계산 -> 안되는 조건이면 포기(백트래킹)
	static int N;
	static int answer;
	static int[] column;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			answer = 0;
			column = new int[N];
		
			chess(0);
			
			System.out.printf("#%d %d\n", tc, answer);
		}// tc
		sc.close();
	}// main
	
	static void chess(int r) {
		if (r == N) {
			answer++;
			return;
		}
		
		for (int c = 0; c < N; c++) {
			if (isValid(r, c)) {
				column[r] = c;
				chess(r + 1);
			}
		}

	}// chess
	
	static boolean isValid(int r, int c) {
		for (int i = 0; i < r; i++) {
			if (column[i] == c || Math.abs(column[i] - c) == Math.abs(i - r))
				return false;
		}
		return true;
	}

}
