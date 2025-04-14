import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] team1;
	static int[] team2;
	static int[][] status;
	static int ans=Integer.MAX_VALUE;
     public static void main(String[] args)throws Exception {
     BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    
     N=Integer.parseInt(br.readLine());
     team1=new int[N/2+1];
     team2=new int[N/2+1];
     status=new int[N+1][N+1];
	     for(int i=1;i<=N;i++) {
	    	 StringTokenizer st =new StringTokenizer(br.readLine());
	    	 for(int k=1;k<=N;k++) {
	    		 status[i][k]=Integer.parseInt(st.nextToken());
	    	 }
	     }
     team1[1]=1;
     makingteam(2,1);
     System.out.println(ans);
   
     }
     static void makingteam(int a,int cnt) {
    	 if(cnt==N/2) {
    		 int count=1;
    		 Loop:for(int i=1;i<=N;i++) {
    			 for(int k=1;k<=N/2;k++) {
    				 if(team1[k]==i)
    					continue Loop;
    			 }
    			 team2[count]=i;
    			 count++;
    		 }
    	ans=Math.min(score(team1,team2,status),ans);
    		 
    		 return;
    	 }
    	 else {
	    	 for(int i=a;i<=N;i++) {
	    		 if(team1[cnt]<i) {
	    		 team1[cnt+1]=i;
	    		 makingteam(i+1,cnt+1);
	    		}
	    	}
    	 }
    	 
     }
     static int score(int[] a,int[] b,int[][] status) {
    	 int asum=0;
    	 for(int i=1;i<=N/2;i++) { 
    		 for(int k=1;k<=N/2;k++) {
    			 if(i==k)continue;
    		    asum+= status[a[i]][a[k]];
    		 }
    	 }
    	 int bsum=0;
    	 for(int i=1;i<=N/2;i++) { 
    		 for(int k=1;k<=N/2;k++) {
    			 if(i==k)continue;
    		    bsum+= status[b[i]][b[k]];
    		 }
    	 }
    	 return Math.abs(asum-bsum);
    	 
     }
 }

