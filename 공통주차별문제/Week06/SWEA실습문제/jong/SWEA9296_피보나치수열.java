package 공통주차별문제.Week06.SWEA실습문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA9296_피보나치수열 {
	static Integer[] dp;
	  public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  // 테스트 케이스 수
         dp=new Integer[10000];
         dp[1]=1;
         dp[2]=1;
         dp[3]=2;
		for (int tc = 1; tc <= 5; tc++) {
		     
			int N = Integer.parseInt(br.readLine());
			System.out.println(f(N));
			
			
		}
  }
	  public static int f(int N) {
		  if(dp[N]==null) {
			  
			  dp[N]=f(N-1)+f(N-2);
			  
		  }
		  return dp[N];
	  }
}
