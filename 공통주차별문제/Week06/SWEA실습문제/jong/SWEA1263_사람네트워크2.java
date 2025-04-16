package 공통주차별문제.Week06.SWEA실습문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1263_사람네트워크2 {
	static int N;
	static int M;
	static int min;
	static List<List<Integer>> list;
	 public static void main(String args[]) throws IOException{
		   BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		   int T=Integer.parseInt(br.readLine());
		   
		   for(int tc=1;tc<=T;tc++) {
			   StringTokenizer st =new StringTokenizer(br.readLine());
			   N=Integer.parseInt(st.nextToken());
			   list=new ArrayList<>();
			   for(int i=0;i<=N;i++) {
				   list.add(new ArrayList<>());
			   }
			   int[][] arr=new int[N+1][N+1];

			   for(int i=1;i<=N;i++) {
				   for(int k=1;k<=N;k++) {
					   int temp=Integer.parseInt(st.nextToken());
					   if(temp==0) arr[i][k]=Integer.MAX_VALUE;
					   else arr[i][k]=1;
				   }
			   }
			  
			   
			   for(int q=1;q<=N;q++) {
				   for(int i=1;i<=N;i++) {
					   for(int k=1;k<=N;k++) {
						   if(i==k) continue;
						  if(arr[i][q]!=Integer.MAX_VALUE&&arr[q][k]!=Integer.MAX_VALUE) {
							  arr[i][k]=Math.min(arr[i][k],arr[i][q]+arr[q][k]);
						  }
					   }
				   }
			   }
			   int min=Integer.MAX_VALUE;
			   for(int i=1;i<=N;i++) {
				   int sum=0;
				   for(int k=1;k<=N;k++) {
					   if(arr[i][k]==Integer.MAX_VALUE)continue;
					   
					   sum+=arr[i][k];
				   }
				   min=Math.min(sum,min);
			   }
			   System.out.println("#"+tc+" "+min);
		   }
	 }

	
}
