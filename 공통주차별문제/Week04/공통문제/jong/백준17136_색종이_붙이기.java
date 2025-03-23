import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


      static int usepapernum=0;
      static int[][] paper;
      static int[] colorleft= {0,5,5,5,5,5};
      static int min=Integer.MAX_VALUE;
     public static void main(String[] args)throws Exception {
     BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
     
    
     
      paper =new int[10][10];
  
     for(int i=0;i<10;i++) {
    	 StringTokenizer st = new StringTokenizer(br.readLine());
    	 for(int k=0;k<10;k++) {
    		 paper[i][k]=Integer.parseInt(st.nextToken());
    	 }
     }

    back(0,0,0);
  
    if(min==Integer.MAX_VALUE) {
    	System.out.println(-1);
    	return;
    }
     
     System.out.println(min);
    }
     
     public static void back(int y,int x,int cnt) {//가로(x) 전부 확인하면 가로(x)==0,세로(y)+1
    	  if(x>9&&y>=9) {//왜 x>9인가? --->x==10 y==9--즉 판에서 벗어났으니 이제 min값 계산
    		  min=Math.min(min, cnt);
    	  }
    	  
    	  if(min<=cnt) return;//어차피 min보다 크면 할 필요 없음.
    	 
    	  if(x>=10) {//이제 다음 Y로 가세요
    		  back(y+1,0,cnt);
    		  return;
    	  }
    	  
    	  if(paper[y][x]==1) {
    		 for(int i=5;i>=1;i--) {
    			 if(colorleft[i]>0&&checkattach(y,x,i)) {//1이 나오면 붙일 수 있는지 확인 
    			    attach(y,x,i,0);//붙일 수 있으면 붙이기
    			    colorleft[i]--;
    			    back(y,x+1,cnt+1);//재귀
    			    attach(y,x,i,1);//다시 떼기
    				colorleft[i]++;
    			 }
    		 }
    	  }else {
    		  back(y,x+1,cnt);
    	  }
     }
     

     public static void attach(int y,int x,int size,int status) {//색종이 붙이기
    	 for(int i=y;i<y+size;i++) {
    		 for(int k=x;k<x+size;k++) {
    			 paper[i][k]=status;
    		 }
    	 }
     }
     
     public static boolean checkattach(int y,int x,int size) {//붙일 수있나? 확인
    	 if(y+size>10||x+size>10) {
    		 return false;
    	 }else {
    		 for(int i=y;i<y+size;i++) {
        		 for(int k=x;k<x+size;k++) {
        			 if(paper[i][k]==0)
        				 return false;
        		 }
        	 }

    	 } 
    	 return true;
     }	
}    
   
