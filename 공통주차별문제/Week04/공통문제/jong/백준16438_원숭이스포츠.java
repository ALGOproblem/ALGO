import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준16438_원숭이스포츠 {
	static int N;
	static StringBuilder sb=new StringBuilder();
	static String str;
    public static void main(String agrs[])throws IOException {
     BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
	  N=Integer.parseInt(br.readLine());
      for(int i=0;i<N;i++) {
    	  if(i%2==0)
    	  sb.append("A");
    	  else {sb.append("B");}
      } sb.append("\n");//시작 ABABABAB담기
       str=sb.toString();
       
       team(1);
   
       System.out.println(sb);
       
   }  
    
   public static void team(int depth) {
	   if(depth==7) {
		   return;
	   }else {
		    expand();//EXPAND후에 N까지 해당 일의 대진표 SB에 담기
		    for(int i=0;i<N;i++) {
		    	sb.append(str.charAt(i));
		    }
		    sb.append("\n");
		    team(depth+1);
	   }   
   }
   
    public static void expand() {
    //A--AB   B-BA로 확장
         StringBuilder sb2=new StringBuilder();
         
         for(int i=0;i<N;i++) {
        	 char temp=str.charAt(i);
        	 if(temp=='A')sb2.append("AB");
        	 else if(temp=='B')sb2.append("BA");
         }
         str=sb2.toString(); 	
    }
    
}
