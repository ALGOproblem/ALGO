
package 공통주차별문제.Week06.SWEA실습문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA1267_작업순서 {
	static StringBuilder sb;
	static   int[] indegree;
	static List<List<Integer>> list ;
	 public static void main(String args[]) throws IOException{
	   BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	   
	   	for(int tc=1;tc<=10;tc++) {
		   StringTokenizer st =new StringTokenizer(br.readLine());
		   
		   int V=Integer.parseInt(st.nextToken());
		   int E=Integer.parseInt(st.nextToken());
		   
		  list =new ArrayList<>();
		   for(int i=0;i<=V;i++) {
			   list.add(new ArrayList<>());
		   }
		   
		   indegree=new int[V+1];
		   
		   st =new StringTokenizer(br.readLine());
		   for(int i=0;i<E;i++) {
			   int from=Integer.parseInt(st.nextToken());
			   int to=Integer.parseInt(st.nextToken());
		      list.get(from).add(to);
		      indegree[to]++;
		   }
		   
		 
		   List<Integer> ans=new ArrayList<>();
		   Stack<Integer> stack =new Stack();
		   sb =new StringBuilder();
		   sb.append("#").append(tc);
		   
		   for(int i=1;i<=V;i++) {
			   if(indegree[i]==0)
				  stack.push(i);
		   }
          
		   while(!stack.isEmpty()) {
			   int now=stack.pop();
			   dfs(now);   
			   
		   }

		   System.out.println(sb);
		   
		   
	   	}
	}
	 public static void dfs(int now) {
		 if(indegree[now]!=0) return;
		 else {
			 sb.append(" ").append(now);
			 for(int next:list.get(now)) {
				 indegree[next]--;
				 dfs(next);
			 }
			 
		 }
		 
		 
	 }
}