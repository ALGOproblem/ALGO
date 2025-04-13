package 공통주차별문제.Week06.공통문제.MIRIM;

import java.util.*;

public class 백준_17615_볼모으기 {
	// 그리드: 현재에서 최선의 선택을 하자
	// 빨간 공 선택 -> 왼쪽, 오른쪽으로 몰기, 파란 공 -> 왼쪽, 오른쪽으로 몰기
	// 경계에 있는 공들은 움직이지 않아도 된다 -> 위 케이스 4개 별로 제외시키기
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 볼의 개수 ~500,000 -> 완전탐색 불가능
		String str = sc.next();
		int R = 0; // 총 빨강의 개수
		
		// 빨간 공, 파랑 공의 수 계산
		for (char c : str.toCharArray()) {
			if (c == 'R') R++;
		}
		int B = N - R;
		
		// 빨간 공 이동, 왼쪽으로 몰아넣기
		int leftRed = 0;
		for (int i = 0; i < N; i++) {
			if (str.charAt(i) == 'B') break;
			leftRed++;
		}
		
		// 빨간 공 이동, 오른쪽으로 몰아넣기
		int rightRed = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (str.charAt(i) == 'B') break;
			rightRed++;
		}
		
		int minRed = R - Math.max(leftRed, rightRed);
		
		// 빨간 공 이동, 왼쪽으로 몰아넣기
		int leftBlue = 0;
		for (int i = 0; i < N; i++) {
			if (str.charAt(i) == 'R') break;
			leftBlue++;
		}
		
		// 빨간 공 이동, 오른쪽으로 몰아넣기
		int rightBlue = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (str.charAt(i) == 'R') break;
			rightBlue++;
		}
		
		int minBlue = B - Math.max(leftBlue, rightBlue);
		
		int min = Math.min(minRed, minBlue);
		
		System.out.println(min);
		
		sc.close();
	}//main
	
}
