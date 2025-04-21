import java.io.*;
import java.util.*;

public class Main {
     static int N;
    static long[][][] dp;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        N=Integer.parseInt(br.readLine());
        arr=new int[N][N];
        for(int i=0;i<N;i++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	for(int k=0;k<N;k++) {
        		arr[i][k]=Integer.parseInt(st.nextToken());
        	}
        }
        
        dp=new long[N][N][3];
        dp[0][1][0]=1;
        
        for(int i=0;i<N;i++) {
        	for(int k=2;k<N;k++) {
        		if(arr[i][k]==1) continue;
        		
        		dp[i][k][0]=dp[i][k-1][0]+dp[i][k-1][2];
        		
        		if(i==0) continue;
        		dp[i][k][1]=dp[i-1][k][1]+dp[i-1][k][2];
        		
        		if(arr[i-1][k]==0&&arr[i][k-1]==0) {
        			dp[i][k][2]=dp[i-1][k-1][0]+dp[i-1][k-1][1]+dp[i-1][k-1][2];
        		}
        		
        	}
        }
        
       System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);
    }
   
    
    
}
