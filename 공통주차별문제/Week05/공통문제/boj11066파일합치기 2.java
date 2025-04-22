package ALGO.공통주차별문제.Week05.공통문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * 파일을 두 개씩 묶어서 처리해야 한다는 건 금방 느꼈는데 점화식을.. 점화식을 못 세웠다.
 * GPT씨의 도움을 받았음.... 근데 아직 이해하지 못하겠엄
 */


public class boj11066파일합치기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        
        // 각 테스트 케이스 처리
        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine().trim());  // 파일의 개수
            int[] file = new int[K + 1];   // 1-indexed
            long[] prefix = new long[K + 1];  // 누적합 배열 (파일 크기의 합)
            
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                file[i] = Integer.parseInt(st.nextToken());
                prefix[i] = prefix[i - 1] + file[i];
            }
            
            // dp[i][j] : i번째부터 j번째 파일까지 합치는 데 필요한 최소 비용
            long[][] dp = new long[K + 1][K + 1];
            
            // 파일 하나만 있을 땐 합칠 필요가 없으므로 비용 0 (dp[i][i] = 0)
            // 구간 길이 2부터 K까지 늘려가며 계산
            for (int len = 2; len <= K; len++) {
                for (int i = 1; i <= K - len + 1; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Long.MAX_VALUE;
                    
                    // 구간 [i, j]를 두 부분으로 나눌 위치 k (i ≤ k < j)
                    for (int k = i; k < j; k++) {
                        long cost = dp[i][k] + dp[k + 1][j] + (prefix[j] - prefix[i - 1]);
                        if (cost < dp[i][j]) {
                            dp[i][j] = cost;
                        }
                    }
                }
            }
            
            sb.append(dp[1][K]).append("\n");
        }
        System.out.print(sb);
    }
}
