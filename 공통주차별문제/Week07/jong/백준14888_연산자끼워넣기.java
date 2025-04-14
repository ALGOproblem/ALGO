import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
    static Deque<Integer> num;
    static ArrayList<Integer> op;
    static Deque<Integer> calnum;
    static int[] operator;
    static int max=Integer.MIN_VALUE;
    static int min=Integer.MAX_VALUE;
     public static void main(String[] args)throws Exception {
	     BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	    
	     N=Integer.parseInt(br.readLine());
	  
    	 StringTokenizer st =new StringTokenizer(br.readLine());
    	 num=new LinkedList<>();
    	 op=new ArrayList<>();
    	 for(int i=0;i<N;i++) {
    		 num.offerLast(Integer.parseInt(st.nextToken()));
    	 }
    	 
    	 operator=new int[4];
    	 st =new StringTokenizer(br.readLine());
    	 for(int i=0;i<4;i++) {
    		 operator[i]=Integer.parseInt(st.nextToken());
    	 }
    	 
    	 back();
    	 System.out.println(max);
    	 System.out.println(min);
    	 
     }
     
    public static void back() {
    	if(op.size()==N-1) {
    		int temp=caculate();
    		max=Math.max(max, temp);
    		min=Math.min(min, temp);
    	}else {
    		for(int i=0;i<4;i++) {
    			if(operator[i]>0) {
    				operator[i]--;
    				op.add(i);
    				back();
    				op.remove(op.size()-1);
    				operator[i]++;
    			}
    		}
    		
    	}
    }
    
    public static int caculate() {
    	calnum=new LinkedList<>(num);
    	int i=0;
    		while(calnum.size()>1) {
    			int cal=op.get(i);
    			int a=calnum.pollFirst();
    			int b=calnum.pollFirst();
    			int temp=0;
    			switch(cal) {
    			case 0:
    				temp=a+b;
    				break;
    			case 1:
    				temp=a-b;
    				break;
    			case 2: 
    				temp=a*b;
    				break;
    			case 3: 
    				temp=a/b;
    				break;		   
    			}
    			
    			calnum.offerFirst(temp);
    			i++;
    		}
    		return calnum.poll();
    	
    }
     
 }