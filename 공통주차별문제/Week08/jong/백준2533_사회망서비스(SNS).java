import java.io.*;
import java.util.*;

public class Main {
     static int N;
    static List<List<Integer>> list;
    static int[][] dp;
    static boolean[] visit; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        N=Integer.parseInt(br.readLine());
        
        list=new ArrayList<>();
        for(int i=0;i<=N;i++) {
        	list.add(new ArrayList<>());
        }
        visit=new boolean[N+1];
        for(int i=0;i<N-1;i++) {
        	StringTokenizer st =new StringTokenizer(br.readLine());
        	int a=Integer.parseInt(st.nextToken());
        	int b=Integer.parseInt(st.nextToken());
        	list.get(a).add(b);
        	list.get(b).add(a);
        	
        	
        }
        
        dp=new int[N+1][2];
         dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
        
    }
    static void dfs(int now) {
    	visit[now]=true;
    	dp[now][0]=0;
    	dp[now][1]=1;
    	
    	for(int next:list.get(now)) {
    		if(!visit[next]) {
    			dfs(next);
    			dp[now][0]+=dp[next][1];
    			dp[now][1]+=Math.min(dp[next][0], dp[next][1]);
    		}
    	}
    	
    }
    
}