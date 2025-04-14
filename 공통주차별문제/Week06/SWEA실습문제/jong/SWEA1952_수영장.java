
package 공통주차별문제.Week06.SWEA실습문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952_수영장 {
	static int[] cost;
	static int[] day;
	static Integer[] dp;
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트케이스 수 입력

		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			cost=new int[5];
			cost[1]=Integer.parseInt(st.nextToken());
			cost[2]=Integer.parseInt(st.nextToken());
			cost[3]=Integer.parseInt(st.nextToken());
			cost[4]=Integer.parseInt(st.nextToken());
         
			 day=new int[13];
			 st =new StringTokenizer(br.readLine());
			 for(int i=1;i<=12;i++) {
				 day[i]=Integer.parseInt(st.nextToken());
			 }
         dp=new Integer[13];
           dp[0]=0;
           dp[1]=Math.min(cost[2],cost[1]*day[1]);
           dp[2]=Math.min(dp[1]+cost[2], dp[1]+cost[1]*day[2]);
      
           
           int ans=dyna(12);
           ans=Math.min(ans, cost[4]);
           System.out.println("#"+tc+" "+ans);
	}	
  }
    	public static int dyna(int K) {
    		if(dp[K]==null) {
   
                dp[K]=Math.min(dyna(K-1)+cost[2], Math.min(dyna(K-1)+day[K]*cost[1],dyna(K-3)+cost[3]));      
    		}
    		return dp[K];
    	}
}