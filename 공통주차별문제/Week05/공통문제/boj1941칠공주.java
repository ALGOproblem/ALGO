package ALGO.공통주차별문제.Week05.공통문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class boj1941칠공주 {
	static int[][] arr;
	static boolean[] visited;
	static int[][] xy = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 3 },
			{ 1, 4 }, { 2, 0 }, { 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 },
			{ 3, 4 }, { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 } };// 25명 학생들의 자리 번호
	static List<int[]> sevenStudent;
	static int N;// 학생수
	static int countS;
	static int answer;
	static int[] dirx = {0, 0, -1, 1};//상하좌우
	static int[] diry = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 5;
		countS = 0;
		answer = 0;
		arr = new int[N][N];
		visited = new boolean[N*N];
		sevenStudent = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				if (s.charAt(j)=='S') arr[i][j] = 1;
				else arr[i][j] = 0;
			}
		} // arr입력
		backTracking(0, 0);
		System.out.println(answer);

	}

	static void backTracking(int depth, int start) {
		if (depth == 7) {
			if(countS>=4 && isConn()){// 's'가 4 이상인지
				answer++;
			}
			return;
		}
		//나온 조합의 수가 서로 연결되어 있지 않다면 return;
		for (int i = start; i < 25; i++) { // 여기에서 헷갈림
			if (!visited[i]) {
				int[] cur = xy[i]; // i번째 학생의 위치
				visited[i] = true;
				countS += arr[cur[0]][cur[1]];
				sevenStudent.add(cur);
				backTracking(depth+1, i+1); // 여기에서 헷갈림
				visited[i] = false;
				countS -= arr[cur[0]][cur[1]];
				sevenStudent.remove(sevenStudent.size()-1); // 빼기
			}
		}
	}//backTracking
	static boolean isConn() {
	    boolean[][] check = new boolean[N][N];
	    Queue<int[]> queue = new LinkedList<>();
	    queue.offer(sevenStudent.get(0));
	    check[sevenStudent.get(0)[0]][sevenStudent.get(0)[1]] = true;
	    int connected = 1;

	    while (!queue.isEmpty()) {
	        int[] cur = queue.poll();
	        for (int d = 0; d < 4; d++) {
	            int nx = cur[0] + dirx[d];
	            int ny = cur[1] + diry[d];
	            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
	            // 7명 안에 포함된 학생이고 아직 안 갔으면
	            for (int[] s : sevenStudent) {
	                if (s[0] == nx && s[1] == ny && !check[nx][ny]) {
	                    check[nx][ny] = true;
	                    queue.offer(new int[]{nx, ny});
	                    connected++;
	                    break;
	                }
	            }
	        }
	    }
	    return connected == 7; // 7명이 다 연결됐으면 true
	}
}
