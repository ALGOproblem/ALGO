import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA14510_나무높이 {
	 public static void main(String args[]) throws IOException{
		   BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		   int tc=Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력
		   
		   for(int T=1;T<=tc;T++) {
			   int N=Integer.parseInt(br.readLine());  // 나무의 개수
			   
			   StringTokenizer st =new StringTokenizer(br.readLine());
			   int[] tree=new int[N];  // 나무 높이 배열
			   for(int i=0;i<N;i++) {
				   tree[i]=Integer.parseInt(st.nextToken());  // 나무 높이 입력
			   }
			   
			   Arrays.sort(tree);  // 오름차순 정렬해서 가장 큰 나무를 기준으로 계산
			   int one=0;  // 1씩 물을 주는 횟수
			   int two=0;  // 2씩 물을 주는 횟수

			   // 가장 큰 나무(tree[N-1])를 기준으로 나머지 나무들이 얼마나 부족한지 계산
			   for(int i=0;i<N-1;i++) {
				   tree[i]=tree[N-1]-tree[i];  // 부족한 높이 차이 계산
				   two+=tree[i]/2;  // 2씩 줄 수 있는 횟수 누적
				   one+=tree[i]%2;  // 나머지 1씩 줘야 하는 횟수 누적
			   }

			   // 두 번씩 주는 것보다 한 번씩 주는 것이 더 많으면 균형 맞추기
			   if(two>one) {
				   while(true) {
					   int range=Math.abs(two-one);
					   if(range<=1) break;  // 차이가 1 이하가 되면 종료
					   
					   one+=2;  // 1씩 주는 날을 2일 추가
					   two--;  // 2씩 주는 날을 1일 줄임
				   }
			   }
			   
			  int ans=0;  // 최소 날짜 수 결과값
			  
			   if(two==one) {
				   ans=2*two;  // 두 개가 같으면 2일씩 정확히 교차 가능
			   }else if(two>one) {
				    ans=2*two;  // 2씩 주는 게 더 많을 경우 그대로 진행
			   }else {
				   ans=2*one-1;  // 1씩 주는 게 더 많으면 하루를 덜 쓰게 됨 (교차 불가)
			   }

			   System.out.println("#"+T+" "+ans);  // 결과 출력
		   }
	 }
}
