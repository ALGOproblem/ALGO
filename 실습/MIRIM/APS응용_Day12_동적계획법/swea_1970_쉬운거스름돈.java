import java.util.Arrays;
import java.util.Scanner;

public class swea_1970_쉬운거스름돈 {
	
	public static void main(String[] args) {
		int[] coins = {1, 5, 10, 50, 100, 500, 1000, 5000};
		int MAX = 100_000;
		
		int[] dp = new int[MAX + 1];
		Arrays.fill(dp, MAX + 1);
		dp[0] = 0;
		int[] used = new int[MAX + 1]; // 가장 최근에 사용한(가장 금액이 큰) 동전의 정보
		
		for (int i = 1; i <= MAX; i++) {
			int minCnt = MAX + 1; // 동전의 개수
			
			for (int j = 0; j < coins.length; j++) {
				int coin = coins[j];
				
				if (i >= coin && minCnt > dp[i - coin] + 1) {
					minCnt = dp[i - coin] + 1;
					used[i] = j; // 가장 최근에 사용한 동전의 인덱스(갱신될수록 커진다)
				}
			}
			
			dp[i] = minCnt;
		}// dp 
		
		// 테스트 케이스
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt() / 10;
			
			int[] count = new int[coins.length];
			int temp = N;
			
			while (temp > 0) {
				int coinIdx = used[temp];
				count[coinIdx]++;
				temp -= coins[coinIdx];
			}
			// 동전 개수 세기
			// 마지막으로 사용한 동전 = 최적해를 이루는 동전에서 가장 큰 동전 값
			// 예, 6150원의 5000원 제외 -> 1150원의 최적해에서 다시 생각, 1000원 사용 -> ...
			
			System.out.printf("#%d\n", tc);
			for (int j = count.length - 1; j >= 0; j--) {
				System.out.print(count[j] + " ");
			}
			System.out.println();
		}
		
		sc.close();
	} //main
	
}
