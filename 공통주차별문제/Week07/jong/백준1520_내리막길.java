import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int[][] dp;
	static int N;
	static int M;
	static int[] diy= {-1,1,0,0};
	static int[] dix= {0,0,-1,1};
	static int ans=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine());
        
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        arr=new int[N+1][M+1];
        for(int i=1;i<=N;i++) {
        	st=new StringTokenizer(br.readLine());
        	for(int k=1;k<=M;k++) {
        		arr[i][k]=Integer.parseInt(st.nextToken());
        	}
        }
        dp=new int[N+1][M+1];
        
        for(int i=1;i<=N;i++) {
        	Arrays.fill(dp[i], -1);        }
        
       dfs(1,1);
        System.out.println(dp[1][1]);
      
        
    }
    public static int dfs(int y,int x) {

    	 if(y==N&&x==M) {
    		 return 1;
    	 }
          
    	 if(dp[y][x]!=-1) return dp[y][x];
    	 
    	  dp[y][x]=0;
    		 for(int i=0;i<4;i++) {
    			 int Y=y+diy[i];
    			 int X=x+dix[i];
    			 if(Y>=1&&Y<=N&&X>=1&&X<=M) {
    				 if(arr[Y][X]<arr[y][x]) {
    						 
    					dp[y][x]+= dfs(Y,X);
    				 }
    			 }
    			 
    		 }
    		 return dp[y][x];
    	 
    }
}