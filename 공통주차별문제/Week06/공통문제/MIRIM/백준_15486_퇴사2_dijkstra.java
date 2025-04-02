package 공통주차별문제.Week06.공통문제.MIRIM;

import java.util.*;

public class 백준_15486_퇴사2_dijkstra {
	static int N;
	static Node[] arr;
	static int maxTotal;
	
	static class Node implements Comparable<Node>{
		int start;
		int time;
		int pay;
		int total;
		
		public Node(int start, int time, int pay, int total) {
			this.start = start;
			this.time = time;
			this.pay = pay;
			this.total = total;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.pay, this.pay);
		}
		
	}// Node

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new Node[N + 2];
		
		for (int i = 1; i <= N; i++) {
			int T = sc.nextInt();
			int P = sc.nextInt();
			
			arr[i] = new Node(i, T, P, P);
		}//입력
		
		maxTotal = 0;
		for (int i = 1; i <= N; i++) {
			if (i + arr[i].time - 1 <= N)
				dijkstra(arr[i]);
		}// 시작일마다 다익스트라
		
		System.out.println(maxTotal);
		
		sc.close();
	}// main
	
	static void dijkstra(Node sNode) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(sNode);
		
		int[] total = new int[N + 2];
		total[sNode.start + sNode.time] = sNode.total;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curDay = cur.start + cur.time;
			
			if (curDay > N + 1) continue;
			maxTotal = Math.max(cur.total, maxTotal);
			
			for (int nextDay = curDay; nextDay <= N; nextDay++) {
				Node next = arr[nextDay];
				if (next == null) continue;
				
				int endDay = nextDay + next.time;
				if (endDay > N + 1) continue;
				
				int nextTotal = cur.total + next.pay;
				if (total[endDay] < nextTotal) {
					total[endDay] = nextTotal;
					pq.offer(new Node(nextDay, next.time, next.pay, nextTotal));
				}
				
			}// 다음 노드 방문
			
		}// while
		
	}// dijkstra

}
