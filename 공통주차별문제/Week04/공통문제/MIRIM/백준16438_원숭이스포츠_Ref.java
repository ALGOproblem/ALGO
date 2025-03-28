package 공통주차별문제.Week04.공통문제.MIRIM;

import java.util.*;

public class 백준16438_원숭이스포츠_Ref {
	static char[][] monkeys; // 7일간 팀 구성
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		monkeys = new char[7][N];

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++)
			sb.append('A');

		binaryTeam(0, N - 1, 0);

		for (int i = 0; i < 7; i++) {
			String str = new String(monkeys[i]);
			
			if (str.equals(sb.toString())) { // sb는 A로만 이뤄짐 -> 팀 구성이 A로만 이뤄져있으면 B로 바꿔줌
				str = str.replaceFirst("A", "B");
			}
			System.out.println(str);
		}
	}

	static void binaryTeam(int start, int end, int day) {
		if (day == 7) {
			return;
		}

		int mid = (start + end) / 2;
		
		for (int i = start; i <= mid; i++)
			monkeys[day][i] = 'A';
		
		for (int i = mid + 1; i <= end; i++)
			monkeys[day][i] = 'B';
		
		binaryTeam(start, mid, day + 1);
		
		binaryTeam(mid + 1, end, day + 1);

	}
}
