package 공통주차별문제.Week05.공통문제.jong

import java.io.*;
import java.util.*;

public class 백준11066_파일합치기 {
    static int[] sum;
    static int[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for(int tc=0;tc<T;tc++) {
        
	        N = Integer.parseInt(br.readLine());
	        int[] files = new int[N + 1];
	        sum = new int[N + 1];
	        dp = new int[N + 1][N + 1];
	
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        for (int i = 1; i <= N; i++) {
	            files[i] = Integer.parseInt(st.nextToken());
	            sum[i] = sum[i - 1] + files[i];
	        }
	
	        for (int len = 2; len <= N; len++) {
	            for (int i = 1; i <= N - len + 1; i++) {
	                int j = i + len - 1;
	                dp[i][j] = Integer.MAX_VALUE;
	                for (int k = i; k < j; k++) {
	                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1]);
	                }
	            }
	        }
	
	        System.out.println(dp[1][N]);
	        
        }
    }
}