package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1차 시도 : dp를 2차원 배열로 풀려다가 fail -> 1차원 배열로 변경
 * 2차 시도 : i를 시작일과 종료일 둘 중 헷갈리다가 시작일로 다시 변환
 * 3차 시도 : 상담 종료일이 N+1일까지라는 것을 GPT가 알려줌 -> dp를 N+2로 변경
 */

public class boj15486퇴사2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 1부터 N까지 사용하므로, 배열 크기는 N+1
        int[] time = new int[N+1];
        int[] profit = new int[N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            profit[i] = Integer.parseInt(st.nextToken());
        }
        
        // 상담 종료일이 N+1일에도 가능하므로 dp 배열 크기를 N+2로 잡음.
        int[] dp = new int[N+2];
        // i는 현재 날짜(1부터 N까지)로 생각.
        for (int i = 1; i <= N; i++) {
            dp[i+1] = Math.max(dp[i+1], dp[i]); // 현재까지의 최대 이익을 다음 날로 전달
            if (i + time[i] <= N + 1) { // 상담을 진행할 경우, 상담 종료일 i + time[i]가 N+1 이하일 때만 가능.
                dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + profit[i]);
            }
        }
        System.out.println(dp[N+1]); // 퇴사일(N+1일)에 얻을 수 있는 최대 이익 출력
    }
}
