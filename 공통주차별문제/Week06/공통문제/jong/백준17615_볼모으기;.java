package 공통주차별문제.Week06.공통문제.jong;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        StringTokenizer st =new StringTokenizer(br.readLine());
	    	
	        int N=Integer.parseInt(st.nextToken()); 
	        String str= br.readLine();
    	
    	    int min=Math.min(cnt('R',str), cnt('B',str));
    	    
    	    if(min!=0) min=Math.min(Math.min(cntreverse('R',str), cntreverse('B',str)), min); 

    	    System.out.println(min);
    }
    public static int cnt(char a,String str) {
    	int idx=str.length()-1;
    	while(idx>=0) {
    		if(str.charAt(idx)==a) idx--;
    		else  break;	
    	}

    	if(idx==-1) return 0;
    	int cnt=0;
    	for(int i=idx;i>=0;i--) {
    		if(str.charAt(i)==a)cnt++;
    	}
    
    	return cnt;
    }
   public static int cntreverse(char a, String str) {
	   int idx=0;
	   while(idx<=str.length()-1) {
		   if(str.charAt(idx)==a) idx++;
		   else break;
	   }
	   
	   if(idx==str.length()) return 0;
	   int cnt=0;
	   for(int i=idx;i<str.length();i++) {
		   if(str.charAt(i)==a)cnt++;
	   }
	   return cnt;
   }
}