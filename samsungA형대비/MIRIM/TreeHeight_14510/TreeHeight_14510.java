import java.util.Scanner;

public class TreeHeight_14510 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			
			int[] height = new int[N];
			int maxHeight = 0;
			
			for (int i = 0; i < N; i++) {
				height[i] = sc.nextInt();
				if (maxHeight < height[i]) maxHeight = height[i];
			} // 입력
			
			int one = 0;
			int two = 0;
			for (int i = 0; i < N; i++) {
				int diff = maxHeight - height[i]; // 높이 차 계산 후 1, 2 숫자 세기
				if(diff != 0) {
					two += diff / 2;
					one += diff % 2;
				}
			}
			
			int answer = 0;

      // 1과 2의 세트 최대 개수 = 2일
			if (one > two) {
				one -= two;
				answer = two * 2 + (one - 1) * 2 + 1;
				
			} else if(one == two) {
				answer = one * 2;
				
			} else if (one < two) {
				answer = one * 2;
				two -= one;

				if (two % 3 == 0) answer += (two / 3) * 4;
				else if (two % 3 == 1) {
					answer += (two / 3) * 4;
					answer += 2;
				} else {
					answer += (two / 3) * 4;
					answer += 3;
				}
			}// 날 수 계산
			
			System.out.printf("#%d %d\n", tc, answer);
		}// tc
		sc.close();
	}

}
