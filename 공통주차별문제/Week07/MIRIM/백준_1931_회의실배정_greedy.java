package 공통주차별문제.Week07.MIRIM;

import java.util.*;

public class 백준_1931_회의실배정_greedy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[][] time = new int[N][2];
		for (int i = 0; i < N; i++) {
			time[i][0] = sc.nextInt();
			time[i][1] = sc.nextInt();
		}
		
		// 끝나는 시간 기준 정렬
		Arrays.sort(time, (a, b) -> {
			if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
			return Integer.compare(a[1], b[1]);
		});
		
		int count = 0;
		int endTime = 0;
		
		for (int i = 0; i < N; i++) {
			if (time[i][0] >= endTime) {
				count++;
				endTime = time[i][1];
			}
		}
		
		System.out.println(count);
		
		sc.close();
	}

}
