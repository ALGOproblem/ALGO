import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 백준16946_벽부수고이동하기4 {
	static int N;
    static int M;
    static int[][] arr;
    static int[][] ans;
    static int[] diy= {-1,1,0,0};
    static int[] dix= {0,0,-1,1};
    static Queue<int[]> qu=new LinkedList<>();
    static boolean[][] visitzero;
   public static void main(String args[]) throws IOException{
	   BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st=new StringTokenizer(br.readLine());
         StringBuilder sb=new StringBuilder();
		  
         N=Integer.parseInt(st.nextToken());
         M=Integer.parseInt(st.nextToken());
	     
         arr=new int[N][M];
         ans=new int[N][M];
         for(int i=0;i<N;i++) {
        	 String str=br.readLine();
        	 for(int k=0;k<M;k++) {
        		 arr[i][k]=str.charAt(k)-'0';
        		 if(arr[i][k]==1) {
        			ans[i][k]=1;//자기 자신도 답에 포함해야하니깐 그리고 추가 배열을 써야함--왜? %10하다가 잘못하면 0으로 인식됨
        		 }
        	 }
         }
         
         visitzero =new boolean[N][M];//static visit
         for(int i=0;i<N;i++) {
        	 for(int k=0;k<M;k++) {
        		 if(arr[i][k]==0&&!visitzero[i][k]) {
        			 visitzero[i][k]=true;
        			 bfs(i,k);
        		 }
        	 }
         }
         

         for(int i=0;i<N;i++) {
        	 for(int k=0;k<M;k++) {
        		 sb.append(ans[i][k]%10);
        	 }
        	 sb.append("\n");
         }
      
    System.out.println(sb);
   }

   // 0의 위치에서 bfs를 실시한다
   // 이때, 탐색하면서 1이나오면 set에 저장---처음에 list에 저장하니 시간초과
   //0의 갯수를 모든 인접 1에 다 준다
   //0은 한번만이라도 visit하면 무조건 막는다--이로써 중복계산 막음.
  //0은 static visit으로 한번 막으면 더이상 가면 안됨(인접 1에 벌써 줬으니깐!)
   
   
   public static void bfs(int y,int x) {
       Set<Integer> walllist=new HashSet<>();
	   Queue<int[]> qu2=new LinkedList<>();
	   qu2.offer(new int[] {y,x});
	   int cnt=1;
	   while(!qu2.isEmpty()) {
		   int[] now=qu2.poll();
		   
		   for(int i=0;i<4;i++) {
			   int Y=now[0]+diy[i];
			   int X=now[1]+dix[i];
			   if(0<=Y&&Y<N&&0<=X&&X<M) {
				  	   
					  if(arr[Y][X]==0&&!visitzero[Y][X]) {//zero면 막고 cnt++
						  cnt++;
						  visitzero[Y][X]=true;
						  qu2.offer(new int[] {Y,X});
					  }else if(arr[Y][X]==1){//인접 1 넣기
						  walllist.add(Y*M+X);
					  }
			   } 
		   }
	   }
    
	   for(int wall:walllist) {
		   ans[wall/M][wall%M]+=cnt;//모든 인접 1에 cnt주기 
	   }
     
   } 
}
