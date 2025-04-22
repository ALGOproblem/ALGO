package 공통주차별문제.Week06.SWEA실습문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3282_0/1knapsack {
	static int[] V;
	static int[] C;
	static Integer[][] dp;
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트케이스 수 입력

		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			V=new int[N+1];
			C=new int[N+1];
			for(int i=1;i<=N;i++) {
				st =new StringTokenizer(br.readLine());
			    V[i]=Integer.parseInt(st.nextToken());	//부피
			    C[i]=Integer.parseInt(st.nextToken());//가치	
			}
			
			
		   dp=new Integer[N+1][K+1];	
		   Arrays.fill(dp[0], 0);
		   System.out.println("#"+tc+" "+dynamic(N,K));
		   
		}
	}
	public static int dynamic(int N,int K) {
	if(dp[N][K]==null) {	
		if(K>=V[N]) {
			dp[N][K]=Math.max(dynamic(N-1,K),dynamic(N-1,K-V[N])+C[N]);
		}else {
			dp[N][K]=dynamic(N-1,K);
		}
	}
		return dp[N][K];
	}
}
