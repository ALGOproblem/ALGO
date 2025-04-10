package 공통주차별문제.Week06.공통문제.jong;

import java.io.*;
import java.util.*;

public class Main {
	static Integer[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        StringTokenizer st =new StringTokenizer(br.readLine());
	    	
	        int N=Integer.parseInt(st.nextToken()); 
            
            dp=new Integer[N+1];
	        dp[0]=0;
	        dp[1]=0;
	        dyna(N);
	        System.out.println(dp[N]);
   }
    static int dyna(int N) {
        if(dp[N]==null) {
        	
        	if(N%6==0) {
        		dp[N]=Math.min(dyna(N/3), Math.min(dyna(N/2), dyna(N-1)))+1;
        	}
        	else if(N%3==0) {
        		dp[N]=Math.min(dyna(N/3), dyna(N-1))+1;
        	}
        	else if(N%2==0) {
        		dp[N]=Math.min(dyna(N/2), dyna(N-1))+1;
        	}else {
        		dp[N]=dyna(N-1)+1;
        	}
        	
        }
    	
    	return dp[N];
    	
    }
}