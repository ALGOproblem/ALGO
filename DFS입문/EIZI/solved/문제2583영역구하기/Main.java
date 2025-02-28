package 문제2583영역구하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static boolean[][] visited;
	static int[] dirx = {-1, 1, 0, 0}; // 상하좌우
	static int[] diry = {0, 0, -1, 1};
	static Queue<int[]> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/문제2583영역구하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int M = Integer.parseInt(st.nextToken()); // 모두 100 이하의 자연수
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[M][N]; // M 행 N 열
		visited = new boolean[M][N];
		// 왼쪽 아래 꼭지점, 오른쪽 위 꼭짓점
		for (int i = 0; i<K; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for (int a = y2-1; a>=y1; a--) {
				for (int b = x1; b<x2; b++) {
					arr[a][b] = 1;
				}
			}
		}// 입력 받기
		List<Integer> area = new ArrayList<>();
		for (int i = M-1; i>=0; i--) {
			for (int j = 0; j<N; j++) {
				if (arr[i][j]==0 && visited[i][j]!=true) {
					int n = bfs(i, j);
					area.add(n);
				}
			}
		}
		Collections.sort(area);
		System.out.println(area.size());
		for (int num : area) {
            System.out.print(num + " ");
        }
		
	}
	public static int bfs(int startX, int startY) {
        int n = 0; // 방문한 칸의 수
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int x = xy[0];
            int y = xy[1];
            n++; // 방문한 노드 카운트 증가

//            System.out.println(x + "," + y + " n의 크기: " + n);

            for (int i = 0; i < 4; i++) {
                int newX = x + dirx[i];
                int newY = y + diry[i];

                // 배열 범위 내에 있고, 방문하지 않은 0인 칸이라면 큐에 추가
                if (newX >= 0 && newX < arr.length && newY >= 0 && newY < arr[0].length) {
                    if (arr[newX][newY] == 0 && !visited[newX][newY]) {
                        queue.add(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
        }
        return n; // 총 방문한 노드 수 반환
    }

}
