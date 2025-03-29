import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA5521_상원이의생일파티 {
	static Set<Integer>[] list;  // 각 사람의 친구 목록을 저장할 배열 (Set 배열 사용)

	 public static void main(String args[]) throws IOException{
		   BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		   int tc=Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력

		   for(int T=1;T<=tc;T++) {
			   
			   StringTokenizer st =new StringTokenizer(br.readLine());
			  
			   int N=Integer.parseInt(st.nextToken());  // 사람 수
			   int M=Integer.parseInt(st.nextToken());  // 친구 관계 수
		        
			   if(M==0) {  // 친구 관계가 없으면 초대할 사람도 없음
		        	System.out.println("#"+T+" "+0);
		        	continue;
		        }
		        
			   list=new HashSet[N+1];  // 인덱스 1부터 사용하기 위해 N+1 크기 할당
               for(int i=0;i<=N;i++) {
				   list[i]=new HashSet<>();  // 각 인덱스에 Set 객체 생성
			   } 
               
			   // 친구 관계 입력 받아 양방향으로 저장
			   for(int i=0;i<M;i++) {
				   st =new StringTokenizer(br.readLine());
				   int a=Integer.parseInt(st.nextToken());
				   int b=Integer.parseInt(st.nextToken());
				   list[a].add(b);
				   list[b].add(a);   
			   }
				  
			     Set<Integer> ans= new HashSet<>();  // 초대할 사람을 저장할 Set (중복 제거용)
				  
				 // 1번 사람(상원)의 친구들을 순회
				  for(int a:list[1]) {
					  ans.add(a);  // 친구는 무조건 초대
					  for(int b:list[a]) {  // 친구의 친구도 확인
						  if(b!=1) ans.add(b);  // 상원 본인은 제외
					  }
				  }

			  // 최종 초대 인원 수 출력
			  System.out.println("#"+T+" "+ans.size());
		   }
	 }
}