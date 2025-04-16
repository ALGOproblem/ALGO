package 공통주차별문제.Week06.SWEA실습문제.jong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA2382_미생물격리 {
	static int[] diy= {0,-1,1,0,0};
	static int[] dix= {0,0,0,-1,1};
	static List<List<int[]>>[] badge;
	static Set<Integer> location;
	static int N;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  // 테스트케이스 수

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
		    N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			badge=new ArrayList[N];
			for(int i=0;i<N;i++) {
				badge[i]=new ArrayList<>();
				for(int k=0;k<N;k++) {
					badge[i].add(new ArrayList<>());
				}
			}
			 location=new HashSet<>();
			for(int i=0;i<K;i++) {
				st =new StringTokenizer(br.readLine());
				int y=Integer.parseInt(st.nextToken());
				int x=Integer.parseInt(st.nextToken());
				int n=Integer.parseInt(st.nextToken());
				int direct=Integer.parseInt(st.nextToken());
				badge[y].get(x).add(new int[] {n,direct});
				location.add(y*1000+x);
			}
		
			for(int i=0;i<M;i++) {
				move();
			}
			int ans=0;
			for(int loca:location) {
				int y=loca/1000;
				int x=loca%1000;
				int[] cell=badge[y].get(x).get(0);
				ans+=cell[0];
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	public static void move() {
		Set<Integer> newlocation=new HashSet<>();
		for(int loca:location) {
			int y=loca/1000;
			int x=loca%1000;
			int[] cell=badge[y].get(x).get(0);
			badge[y].get(x).remove(0);
			y+=diy[cell[1]];
			x+=dix[cell[1]];

			if(y==0||y==N-1||x==0||x==N-1) {
				if(cell[1]==1) cell[1]=2;
				else if(cell[1]==2)	cell[1]=1;
				else if(cell[1]==3)	cell[1]=4;
				else cell[1]=3;
				cell[0]/=2;
			}
			if(cell[0]!=0) {
				badge[y].get(x).add(new int[] {cell[0],cell[1]});
				newlocation.add(y*1000+x);
			}
			
			
		}

		for(int loca:newlocation) {
			int y=loca/1000;
			int x=loca%1000;
			if(badge[y].get(x).size()>1) {
				int max=0;int sum=0; int direct=0;
				while(!badge[y].get(x).isEmpty()) {
						int[] cell=badge[y].get(x).get(0);
						sum+=cell[0];
						if(max<cell[0]) {
							max=cell[0];
							direct=cell[1];
						}
						badge[y].get(x).remove(0);
				}
				badge[y].get(x).add(new int[] {sum,direct});
			}
		}
		location=newlocation;
	}
}