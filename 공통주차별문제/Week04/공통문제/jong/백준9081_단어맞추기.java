import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준9081_단어맞추기 {
	static int N;
   public static void main(String args[]) throws IOException{
	   BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st=new StringTokenizer(br.readLine());
		 
         int T=Integer.parseInt(st.nextToken());
       
         for(int Tc=1;Tc<=T;Tc++) {
        	 StringBuilder sb=new StringBuilder();
        	 String str=br.readLine();
        	 int length=str.length();
        	
        	 
        	int changeidx=-1;
        	int value=-1;
        	 for(int i=length-1;i>0;i--) {// 여기는 바뀌는 부분 체크 즉, 뒤에서부터 오름차순확인하다가 오름차순 아니면 그 부분이 문제!
        		 if(str.charAt(i)>str.charAt(i-1)) {
        			 changeidx=i-1;
        			 value=(int)str.charAt(i-1);
        			 break;
        		 }        		 
        	 }
        	 if(changeidx==-1) {//쭉 오름차순이므로 가장 큰값임 그냥 그대로 print하면됨
        		 System.out.println(str);
        		continue;
        	 }
        	 
        	 for(int i=0;i<changeidx;i++) {
        		 sb.append(str.charAt(i));
        	 }
        	 
        	 boolean check=false;
        	 PriorityQueue<Character> pq=new PriorityQueue<>();
        	 for(int i=length-1;i>=changeidx;i--) {

	        	 if(value<str.charAt(i)&&!check) {//이제 오름차순 깨지는 부분값을 갖고 다시 뒤로가서 그 값보다 큰값을 찾아서 교환 
	        		 sb.append(str.charAt(i));//교환하기 귀찮아서 바로 sb에 담기
	        		 check=true;
	        	 }
        		 else {
        			 pq.offer(str.charAt(i));//pq에 넣어서 교환이후, 내림차순으로 변경!
        		 	}
        		 
        	 }
        	 
      while(!pq.isEmpty()) {
    	  sb.append(pq.poll());//sb에 담기 
      }
	    System.out.println(sb);
	    	 
	     }
       
   } 
}
