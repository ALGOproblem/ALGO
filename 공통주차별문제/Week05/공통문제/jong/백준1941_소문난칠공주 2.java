package 공통주차별문제.Week05.공통문제.jong

import java.io.*;
import java.util.*;

public class 백준1941_소문난칠공주 {
   static char[][] makjang;
   static boolean[] visit;
   static int[] diy= {-1,1,0,0};
   static int[] dix= {0,0,-1,1};
   static int[] sevenprincess;
   static int ans=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        makjang=new char[5][5];
        visit=new boolean[25];
 
        for(int i=0;i<5;i++) {
        	String str=br.readLine();
        	for(int k=0;k<5;k++) {
        		makjang[i][k]=str.charAt(k);
        	}
        }
        
        sevenprincess=new int[7];
  
        back(0,0,0);
        	
        	
       System.out.println(ans); 
        
    }
    public static void back(int a,int depth,int Scnt) { 
       if(depth-Scnt>=4) return;
    	if(depth==7) {
    		if(Scnt>=4) {
    			
    			ans+=bfs();	 
    		}
    	}else {
    		 	for(int i=a;i<25;i++) {			
    				if(!visit[i]) {
    					sevenprincess[depth]=i;
    					visit[i]=true;
	    				if(makjang[i/5][i%5]=='S') back(i+1,depth+1,Scnt+1);
	    				else back(i+1,depth+1,Scnt);
	    				visit[i]=false;
    				}
    			}
    			
    		}
    }

    public static int bfs() {
    	Queue<int[]> qu =new LinkedList<>(); 
    	boolean[][] bfsvisit =new boolean[5][5];
    	int y=sevenprincess[0]/5;
    	int x=sevenprincess[0]%5;
    	bfsvisit[y][x]=true;
    	qu.offer(new int[] {y,x,1});
    	int cnt=1;
    	while(!qu.isEmpty()) {
            int[] now=qu.poll(); 
            
            
	    	for(int i=0;i<4;i++) {
				int Y=now[0]+diy[i];
				int X=now[1]+dix[i];
				if(0<=Y&&Y<5&&0<=X&&X<5) { 
	               if(visit[Y*5+X]&&!bfsvisit[Y][X]) {
	            	   bfsvisit[Y][X]=true;
	            	   cnt++;
	            	   qu.offer(new int[] {Y,X,now[2]+1});
	               }
				}
	    	} 
    	}
    	if(cnt==7)return 1;
    	return 0;
    }
    
 }