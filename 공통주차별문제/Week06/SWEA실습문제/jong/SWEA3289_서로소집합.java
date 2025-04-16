package 공통주차별문제.Week06.SWEA실습문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3289_서로소집합 {
	static int N;
    static int M;
    static int[] parent;
    
    static int find(int a) {
    	if(parent[a]==a) return a;
    	else {return parent[a]=find(parent[a]);}
    }
    
    static void union(int a, int b) {
    	int rootA=find(a);
    	int rootB=find(b);
    	if(rootA!=rootB)  parent[rootB]=rootA;
    }
    
	public static void main(String args[]) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력
	        
		   
			for(int tc = 1; tc <= T; tc++) {
				StringBuilder sb=new StringBuilder();
				StringTokenizer st= new StringTokenizer(br.readLine());
				N=Integer.parseInt(st.nextToken());
				M=Integer.parseInt(st.nextToken());
				
				parent=new int[N+1];
				for(int i=1;i<=N;i++) {
					parent[i]=i;
				}
				
				for(int i=0;i<M;i++) {
					st= new StringTokenizer(br.readLine());
					int act=Integer.parseInt(st.nextToken());
					int a=Integer.parseInt(st.nextToken());
					int b=Integer.parseInt(st.nextToken());
					
					if(act==0) union(a,b);
					else sb.append(check(a,b));
	
				}
				
				System.out.println("#"+tc+" "+sb);
				
			}
	}
	public static int check(int a,int b) {
		int rootA=find(a);
    	int rootB=find(b);
    	if(rootA==rootB) return 1;
    	else {return 0;}
    	
	}
}