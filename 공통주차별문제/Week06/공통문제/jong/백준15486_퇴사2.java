package 공통주차별문제.Week06.공통문제.jong;

import java.io.*;
import java.util.*;

public class Main {
	static int[] day;
	static int[] cost;
	static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        StringTokenizer st =new StringTokenizer(br.readLine());
	    	
	        int N=Integer.parseInt(st.nextToken()); 
            day=new int[N+1];
            cost=new int[N+1];
            for(int i=1;i<=N;i++) {
            	st =new StringTokenizer(br.readLine());
            	day[i]=Integer.parseInt(st.nextToken());
            	cost[i]=Integer.parseInt(st.nextToken());
            }
    	 
    	  dp=new int[N+2];
	       
    	  for(int i=1;i<=N;i++) {
    		  dp[i]=Math.max(dp[i-1], dp[i]);
    		  
    		  if(i+day[i]<=N+1) {
    			  dp[i+day[i]]=Math.max(dp[i+day[i]], dp[i]+cost[i]);
    		  }
    	  }
    	  dp[N+1]=Math.max(dp[N+1], dp[N]);
	      System.out.println(dp[N+1]);  
   }
    
}