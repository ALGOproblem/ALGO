package 공통주차별문제.Week05.공통문제.jong

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 백준1446_지름길 {
	static int N;
     static int[] dist;
   public static void main(String args[]) throws IOException{
	   BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st=new StringTokenizer(br.readLine());
	      N=Integer.parseInt(st.nextToken());
          int D=Integer.parseInt(st.nextToken());
          
          dist=new int[D+1];
          Map<Integer,List<int[]>> map =new HashMap<>();
          for(int i=0;i<N;i++) {
        	  st=new StringTokenizer(br.readLine());
        	  int from=Integer.parseInt(st.nextToken());
        	  int to=Integer.parseInt(st.nextToken());
        	  int cost=Integer.parseInt(st.nextToken());
        	  
        	  if(!map.containsKey(to)) {
        		  List<int[]> list=new ArrayList<>();
        		  list.add(new int[] {from,cost});
        		  map.put(to, list);
        	  }else {
        		  map.get(to).add(new int[] {from,cost});
        	  }
        	  
          }
	     
          for(int i=1;i<=D;i++) {
        	  if(!map.containsKey(i)) {
        		  dist[i]=dist[i-1]+1;
        	  }else {
        		  dist[i]=dist[i-1]+1;
        		  for(int[] before:map.get(i)) {
        			  dist[i]=Math.min(dist[i], dist[before[0]]+before[1]);
        		  }
        	  }
          }
	   System.out.println(dist[D]);
   }
   
}