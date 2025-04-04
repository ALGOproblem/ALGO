package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// - N + 1 일 째에는 회사에 없다.
// 1 <= N <= 1_500_000

// 1. 이제까지 최고 금액을 누적해서 dp 저장
// 2. 상담이 끝나는 날 페이 <  현재 페이 + 받을 페이 일 경우 dp 갱신

// 이전까지의 금액을 누적 안해서 헤맸음 :(

public class 백준_15486_퇴사2 {
    static BufferedReader input;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in));
        input = new BufferedReader(new FileReader("input.txt"));

        int N = Integer.parseInt(input.readLine());

        int[] T = new int[N + 1]; // N일까지
        int[] P = new int[N + 1]; // N일까지
        int[] dp = new int[N + 2]; // N+1 일까지

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(input.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            // 이전까지의 최댓값을 가져와야 한다!!
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            int time = T[i] + i;
            int pay = dp[i] + P[i];

            // N일까지 계산을 N+1 배열에서 받을 것이기 때문에 +2
            if (time < N + 2) {
                dp[time] = Math.max(dp[time], pay);
            }
        }
        System.out.println(dp[N + 1]);
    }
}
