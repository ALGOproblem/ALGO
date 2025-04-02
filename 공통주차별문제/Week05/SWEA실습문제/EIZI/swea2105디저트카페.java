

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * bfs로는 못 푸는 문제 (사각형이 찌그러짐)
 * 
 * 1. 모든 마름모꼴 뽑는 조합 계산 (1~N-2까지) (왼쪽대각선, 오른쪽 대각선만 뽑으면 왼대 오대 왼대 오대)
 * 		2. 모든 포인터(0,0 ~ N-1,N-1)에서 해당 마름모꼴 성립? 확인
 * 			3. isRectangle? (범위 벗어남? 같은 숫자 만남?-hashSet 이미 방문?-visited) return;
 * 			3. 30분동안 헤맨 거 -> 바로 set에 포함 됨? 해버리면 ((마지막은 다시 돌아오기 때문에)) 문제가 생김 다 -1 됨
 * 		2. maxSum = Math.max(maxSum, sum);
 */
public class swea2105디저트카페 {
	static int[][] cafes;
	static int[] rect;
	static int[] dirx = { 1, 1, -1, -1 };
	static int[] diry = { 1, -1, -1, 1 };


	static int N; // 4<=N<=20
	static int maxSum;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/swea2105디저트카페.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(br.readLine());
			cafes = new int[N][N];
			rect = new int[2];
			maxSum = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cafes[i][j] = Integer.parseInt(st.nextToken());
				}
			} // cafes입력
			comb(0);
			System.out.println("#"+test+" "+(maxSum==0?-1:maxSum));
		}

	}

	static void comb(int depth) {
	    if (depth == 2) {
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                // 사각형 성립 가능한 시작점만 탐색
	            	if (i + rect[0] + rect[1] < N && j + rect[0] < N && j - rect[1] >= 0) {
	                    int sum = isRectangle(i, j);
	                    if (sum != -1) {
	                        maxSum = Math.max(maxSum, sum);
	                    }
	                }
	            }
	        }
	        return;
	    }
	    for (int i = 1; i < N - 1; i++) {
	        rect[depth] = i;
	        comb(depth + 1);
	    }
	}


	static int isRectangle(int x, int y) {
	    Set<Integer> set = new HashSet<>();
	    int startDessert = cafes[x][y];
	    set.add(startDessert);
	    int curx = x, cury = y;

	    for (int i = 0; i < 4; i++) {
	        int move = (i % 2 == 0) ? rect[0] : rect[1];
	        for (int j = 0; j < move; j++) {
	            curx += dirx[i];
	            cury += diry[i];
	            if (curx < 0 || cury < 0 || curx >= N || cury >= N) return -1;
	            // 마지막 이동: 출발점으로 돌아와야 함.
	            if (i == 3 && j == move - 1) { // 무턱대고 그냥 바로 set에 포함?  return 해버려서 마지막에 돌아오는 걸 몰랐음..
	                if (curx != x || cury != y) return -1;
	                // 마지막 칸은 중복 체크 하지 않음.
	            } else {
	                if (set.contains(cafes[curx][cury])) return -1;
	                set.add(cafes[curx][cury]);
	            }
	        }
	    }
	    // 이미 마지막 이동에서 출발점 복귀 체크를 했으므로 불필요
	    return set.size();
	}



}
