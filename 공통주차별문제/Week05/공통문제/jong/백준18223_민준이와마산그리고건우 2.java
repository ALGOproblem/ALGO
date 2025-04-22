package 공통주차별문제.Week05.공통문제.jong

import java.io.*;
import java.util.*;

public class 백준18223_민준이와마산그리고건우 {
    static int V;
    static int P;
    static List<List<int[]>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

         V=Integer.parseInt(st.nextToken());
         int E=Integer.parseInt(st.nextToken());
         P=Integer.parseInt(st.nextToken());//건우 place
         list=new ArrayList<>();
         for(int i=0;i<=V;i++) {
        	 list.add(new ArrayList<>());
         }
         
         for(int i=0;i<E;i++) {
        	 st =new StringTokenizer(br.readLine());
        	int start=  Integer.parseInt(st.nextToken()); 
        	int end=  Integer.parseInt(st.nextToken()); 
        	int cost=  Integer.parseInt(st.nextToken()); 
        	list.get(start).add(new int[] {end,cost});
        	list.get(end).add(new int[] {start,cost});
         }
         if(P==1) {
        	 System.out.println("SAVE HIM");
        	 return;
         }
         
         if(dij())System.out.println("SAVE HIM");
         else System.out.println("GOOD BYE");
    }
    public static boolean dij() {
    	PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
    		@Override
    		public int compare(int[] a,int [] b) {
    			return Integer.compare(a[1], b[1]);
    		}
    	});
    	int[] dist=new int[V+1];
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	dist[1]=0;
    	pq.offer(new int[] {1,0,0});
    	while(!pq.isEmpty()) {
    		int[] now=pq.poll();
  
    		if(now[0]==V) {
    			if(now[2]==1) return true;
    			else {continue;}
    		}
    		
    		for(int[] next:list.get(now[0])) {
    			if(dist[next[0]]>=now[1]+next[1]) {
    				dist[next[0]]=now[1]+next[1];
    				
    				if(now[2]==1||next[0]==P) pq.offer(new int[] {next[0],dist[next[0]],1});
    				else pq.offer(new int[] {next[0],dist[next[0]],0});
    					
    			}
    		}
    	} 	
    	return false;
    }
    
}