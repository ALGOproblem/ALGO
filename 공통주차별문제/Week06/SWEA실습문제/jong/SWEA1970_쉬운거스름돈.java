package 공통주차별문제.Week06.SWEA실습문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1970_쉬운거스름돈 {
	static int[] dp;
	static int N;
	static int[] cost= {10,50,100,500,1000,5000,10000,50000};
	  public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  // 테스트 케이스 수
     
       int T=Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
		    dp=new int[8];
			 N = Integer.parseInt(br.readLine());
			System.out.println("#"+tc);
			
			f(7);
			
			for(int i=7;i>=0;i--) {
				System.out.print(dp[i]+" ");
			}System.out.println();
		}
  }
	  public static void f(int depth) {
		  if(depth==-1||N==0) return;
		  
		  dp[depth]=N/cost[depth];
		  N%=cost[depth];
		  f(depth-1);
	  }
}

