import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
    static class meeting implements Comparable<meeting>{
    	int start;
    	int end;
    	
    	meeting(int start,int end){
    		this.start=start;
    		this.end=end;
    	}
    	
    	@Override
    	public int compareTo(meeting a) {
    		if(this.end!=a.end)
    		return Integer.compare(this.end, a.end);
    		else return Integer.compare(this.start,a.start);
    	}
    	
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine());
        PriorityQueue<meeting> pq=new PriorityQueue<>();
        
        int N=Integer.parseInt(st.nextToken());
       
        for(int i=0;i<N;i++) {
        	st=new StringTokenizer(br.readLine());
        	int start=Integer.parseInt(st.nextToken());
        	int end=Integer.parseInt(st.nextToken());
        	pq.offer(new meeting(start,end));
        }
        int ans=0;
        int end=0;
        while(!pq.isEmpty()) {
        	meeting temp=pq.poll();
      
        	if(end<=temp.start) {
        		end=temp.end;
        		ans++;
        	}
        }
        
    	 System.out.println(ans);
    }
}