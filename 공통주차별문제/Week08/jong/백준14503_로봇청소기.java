import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] house;
	static int[] diy= {-1,0,1,0};
	static int[] dix= {0,1,0,-1};
	static int stop=0;
	static int cleancnt=0;
     public static void main(String[] args)throws Exception {
	     BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	     StringTokenizer st =new StringTokenizer(br.readLine());
	     N=Integer.parseInt(st.nextToken());
	     M=Integer.parseInt(st.nextToken());
	     
	      st =new StringTokenizer(br.readLine()); 
	      int startY =Integer.parseInt(st.nextToken());
	      int startX =Integer.parseInt(st.nextToken());
	      int direction=Integer.parseInt(st.nextToken());
	      
	     house=new int[N][M];
	      for(int i=0;i<N;i++) {
	    	  st =new StringTokenizer(br.readLine()); 
	    	  for(int k=0;k<M;k++) {
	    		  house[i][k]=Integer.parseInt(st.nextToken());
	    	  }
	      }
	        
	      while(stop==0) {
	    	  if(house[startY][startX]==0) {
	    	  house[startY][startX]=2;
				 cleancnt++;}
				
	    	  int num=check(startY,startX,direction);
	    
		    	  if(num>=0&&num<4) {
			    		  startY+=diy[num];
			    		  startX+=dix[num];
			    		  direction=num;
		    	  }
		    	  else if(num==4) {
			    		  int temp=direction;
			    		  if(direction==0) direction=2;
			    		  else if(direction==2) direction=0;
			    		  else {direction=4-direction;}
			    		
			    		  startY+=diy[direction];
			    		  startX+=dix[direction];
			    	    if(house[startY][startX]==1)
			    	    	    stop++;
			    	    	 
			    			  direction=temp;
		    	  }  
	      }
	   
	      System.out.println(cleancnt);
	      
     }
     
     static int check(int robotY,int robotX,int direction) {
    
    	 for(int i=direction-1;i>direction-5;i--) {   
    		 int temp=i;
    		 if(temp<0) temp+=4;
    		 int Y=robotY+diy[temp];
    		 int X=robotX+dix[temp];
    		 if(Y>=0&&Y<N&&X>=0&&X<M) {
    			 if(house[Y][X]==0) {
    				 return temp;
    			 }
    		 }
    	 }
    	 return 4;
     }
     
 }