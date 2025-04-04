package 공통주차별문제.Week06.공통문제.MIRIM;

import java.util.*;

public class 백준_1463_1로만들기 {
	// 경로 탐색 -> 최소 비용 -> bfs

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {num, 0});
		
		int[] distance = new int[num + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[num] = 0;
		
		while (!queue.isEmpty()) {
			int[] curNode = queue.poll();
			int cur = curNode[0];
			int curDis = curNode[1];
			
			if (cur == 1) break;
			if (curDis > distance[cur]) continue;
			
			if (cur % 3 == 0 && curDis + 1 < distance[cur / 3]) {
				distance[cur / 3] = curDis + 1;
				queue.offer(new int[] {cur / 3, curDis + 1});
			} 
			
			if (cur % 2 == 0 && curDis + 1 < distance[cur / 2]) {
				distance[cur / 2] = curDis + 1;
				queue.offer(new int[] {cur / 2, curDis + 1});
			}
			
			if (curDis + 1 < distance[cur - 1]) {
				distance[cur - 1] = curDis + 1;
				queue.offer(new int[] {cur - 1, curDis + 1});
			}
			
		}// while
			
		System.out.println(distance[1]);

		sc.close();
	}//main

}
