package 공통주차별문제.Week06.SWEA실습문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1486_장훈이의높은선반 {
	static int min=Integer.MAX_VALUE;
	static boolean[] visit;
	static int N;
	static int B;
	static int[] worker;
	 public static void main(String args[]) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수 입력
	        for(int tc=1;tc<=T;tc++) {
	        	StringTokenizer st=new StringTokenizer(br.readLine());
	        	 min=Integer.MAX_VALUE;
	        	N=Integer.parseInt(st.nextToken());
	        	 B=Integer.parseInt(st.nextToken());
	        	
	        	 worker=new int[N];
	        	visit=new boolean[N];
	        	int maxdepth=N;
	        	st=new StringTokenizer(br.readLine());
	        	for(int i=0;i<N;i++) {
	        		worker[i]=Integer.parseInt(st.nextToken());
	        		if(worker[i]>=B) {
	        			min=Math.min(min, worker[i]-B); 
	        			visit[i]=true;
	        			maxdepth--;
	        		}
	        	}
	        	
	        	for(int i=2;i<=maxdepth;i++) {
	        		back(0,i,0,0);
	        	}
	        	System.out.println("#"+tc+" "+min);
	        }
	 }
	 public static void back(int a, int target,int depth,int sum) {
		 if(depth==target&&sum>=B) {
			 min=Math.min(min,sum-B);
		 }else {
			 int hsum=sum;
			 for(int i=a;i<N;i++) {
				 if(!visit[i]) {
					 visit[i]=true;
					 hsum+=worker[i];
					 back(i+1,target,depth+1,hsum);
					 visit[i]=false;
					 hsum-=worker[i];
				 }
			 }
		 }
	 }
}
