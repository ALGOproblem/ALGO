package 공통주차별문제.Week06.공통문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
     static List<List<int[]>> list;
     static int ans=0;
     static int[] dist;
   public static void main(String args[]) throws IOException{
	   BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st=new StringTokenizer(br.readLine());
	      N=Integer.parseInt(st.nextToken());
          int M=Integer.parseInt(br.readLine());
          
          list=new ArrayList<>();
          for(int i=0;i<=N;i++) {
        	  list.add(new ArrayList<>());
          }
          
          for(int i=0;i<M;i++) {
        	  st=new StringTokenizer(br.readLine());
 
        	  int start=Integer.parseInt(st.nextToken());
        	  int end=Integer.parseInt(st.nextToken());
        	  int cost=Integer.parseInt(st.nextToken());
        	  
        	  list.get(start).add(new int[] {end,cost});
          }
          st=new StringTokenizer(br.readLine());
          int start=Integer.parseInt(st.nextToken());
          int end=Integer.parseInt(st.nextToken());
         
	      dij(start,end);
	      System.out.println(dist[end]);
	      
	   
   }
   public static void dij(int start,int end) {
	   PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int []>() {
		   @Override
		   public int compare(int[] a,int[] b) {
			   if(a[1]!=b[1]) return Integer.compare(a[1],b[1]);
			   else {return Integer.compare(a[0], b[0]);}
		   }
	   });
	   pq.offer(new int[] {start,0});
	   dist=new int[N+1];
	   Arrays.fill(dist,Integer.MAX_VALUE);
	   dist[start]=0;
	   while(!pq.isEmpty()) {
		   int[] now=pq.poll();

		   if(dist[now[0]]<now[1]) {
			   continue;
		   }

		   for(int[] next:list.get(now[0])) {
			   if(dist[next[0]]>dist[now[0]]+next[1]) {

				   dist[next[0]]=dist[now[0]]+next[1];
				   pq.offer(new int[] {next[0],dist[next[0]]});
			   }
		   }
		   
		   
		   
	   }
	   
	   
   }
   
}