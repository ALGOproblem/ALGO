import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1238_Contact {
	static int N;
    static int start;
    static int max;
    
    // 노드 클래스 정의
    static class Node{
    	int value;
    	List<Node> calling;
    	
    	Node(int value){
    		this.value=value;
    		calling=new ArrayList<>();
    	}
    	
    	public void add(Node a) {
    		calling.add(a);
    	}
    }
    
    static Node[] call;
    static boolean[] visit;
    
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    for(int tc=1;tc<=10;tc++) {
			StringTokenizer st =new StringTokenizer(br.readLine());	
			N=Integer.parseInt(st.nextToken()); // 간선 개수
			start=Integer.parseInt(st.nextToken()); // 시작 노드
			
			st =new StringTokenizer(br.readLine());	
			call=new Node[101]; // 노드 배열 초기화
			
			for(int i=0;i<N/2;i++) {
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				
				if(call[from]==null) call[from]=new Node(from);
				if(call[to]==null) call[to]=new Node(to);
				
				call[from].add(call[to]); // 단방향 연결
			}
		 
			visit=new boolean[101]; // 방문 체크 배열
			max=0;
			System.out.println("#"+tc+" "+bfs()); // 결과 출력
	   	}
	}
	
	public static int bfs() {
		int ans=0;
		Queue<int []> qu=new LinkedList<>();
		qu.offer(new int[] {start,1}); // 시작 노드와 깊이(레벨) 1로 큐에 넣기
		visit[start]=true;
		
		while(!qu.isEmpty()) {
			int[] now=qu.poll(); // 현재 노드
			
	        if(max<now[1]) {
	        	max=now[1]; // 더 깊은 레벨 발견 시 max 갱신
	        	ans=call[now[0]].value;
	        }
	        else if(max==now[1]) ans=Math.max(ans, call[now[0]].value); // 같은 레벨이면 번호 큰 값 저장
			
			for(Node next:call[now[0]].calling) { // 인접 노드 순회
				if(!visit[next.value]) {
					visit[next.value]=true;
					qu.offer(new int[] {next.value,now[1]+1}); // 다음 노드와 깊이 +1
				}
			}
		}
		return ans; // 가장 나중에 연락받은 사람 중 번호가 가장 큰 사람
	}
}