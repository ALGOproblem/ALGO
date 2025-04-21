import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static int T;
	static ArrayList<int[]>[] room;
	static int[] diy= {-1,1,0,0};
	static int[] dix= {0,0,1,-1};
	static Queue<int[]> aircondi;
     public static void main(String args[]) throws IOException{
      BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st =new StringTokenizer(br.readLine());
      R =Integer.parseInt(st.nextToken());
      C =Integer.parseInt(st.nextToken());
      T=Integer.parseInt(st.nextToken());
      
     room=new ArrayList[R];
     for(int i=0;i<R;i++) {
    	 room[i]=new ArrayList<>();
     }
      aircondi=new LinkedList<>();
     for(int i=0;i<R;i++) {
    	 st =new StringTokenizer(br.readLine());
    	 for(int k=0;k<C;k++) {	 
    	 int a=Integer.parseInt(st.nextToken());
    	 if(a==-1) aircondi.offer(new int[] {i,k});
    	  room[i].add(new int[] {a,a/5});
    	 }
     }
      
     for(int i=1;i<=T;i++) {
    	 diffusion();
    	 upmove();
    	 downmove();
     }
     int sum=0;
     for(int i=0;i<R;i++) {
    	 for(int k=0;k<C;k++) {
    		sum+=room[i].get(k)[0];
    	 }
     }
   System.out.println(sum+2);
      
     }
     public static void diffusion() {
    	 
    	for(int i=0;i<R;i++) {
    		for(int k=0;k<C;k++) {
    			int diffusionrate=room[i].get(k)[1];
    			if(room[i].get(k)[0]>0&&diffusionrate>0) {
    				for(int q=0;q<4;q++) {
    					int Y=i+diy[q];
    					int X=k+dix[q];
    					if(X>=0&&X<C&&Y>=0&&Y<R) {
    						if(room[Y].get(X)[0]>=0) {
    						room[Y].get(X)[0]+=diffusionrate;
    						room[i].get(k)[0]-=diffusionrate;}
    					}
    				}
    			}
    		}
    	}
    	 newdiffusionrate();
    	 
     }
     public static void upmove() {
    	 int[] uploca=aircondi.poll();
    	 int Y=uploca[0]-1;
    	 while(Y>0) {
    		 room[Y].remove(0);
    		 room[Y].add(0,room[Y-1].get(0));
    		 Y--; 
    	 }
    	  room[Y].remove(0);
    	  room[Y].add(room[Y+1].get(C-1));
    	 Y++; 
    	  while(Y<uploca[0]) {
    		  room[Y].remove(C-1);
    		  room[Y].add(room[Y+1].get(C-1));
    		  Y++;
    	  }
    	 room[Y].add(1,new int[] {0,0});
    	 room[Y].remove(C);
    	 aircondi.offer(uploca);
    	 
    	 
     }
     public static void downmove() {
    	 int[] downloca=aircondi.poll();
    	 int Y=downloca[0]+1;
    	
    	 while(Y<R-1) {
    		 room[Y].remove(0);
    		 room[Y].add(0,room[Y+1].get(0));
    		 Y++;
    	 }
    	 room[Y].remove(0);
    	 room[Y].add(room[Y-1].get(C-1));
    	 Y--;
    	 while(Y>downloca[0]) {
    		 room[Y].remove(C-1);
    		 room[Y].add(room[Y-1].get(C-1));
    		 Y--;
    	 }
    	 room[Y].add(1,new int[] {0,0});
    	 room[Y].remove(C);
    	 aircondi.offer(downloca);
     }
     
     
     public static void newdiffusionrate() {
    		for(int i=0;i<R;i++) {
        		for(int k=0;k<C;k++) {
        		 if(room[i].get(k)[0]>0) {
        			 room[i].get(k)[1]=room[i].get(k)[0]/5;
        		 }
        			
        		}
        	}
     }
     
}
