
/*
 * N*M의 행렬로 표현되는 맵. (0이동, 1이동X)
 * -> 기존 BFS (시간초과..) N,M<=1000
 * -> memo 활용하자...
 *      1. group을 활용하자....... (그냥 연결된 모든 칸을 다 같은 숫자로 채워 넣음 -> 벽 부수면서 새로 연결되는 경우 못 찾음)
 *      2. '0'을 발견하면 group[][] -> 0끼리 연결된 group에 속하면 groupNum으로 바뀜  && groupSize[groupNum] = 그룹의 사이즈 (visited대신 group[][]==0) 활용
 *      3. '1'을 발견하면 HashSet을 활용해서 groupNum이 없으면 추가하고 sum에 더하기기
 *      
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 백준16949벽부수고이동하기4 {
    static int N;
    static int M;
    static int[][] arr;
    static int[][] group;
    static int[] groupSize;
    static int groupNum = 2;  // 0, 1은 기존 map 값이니까 2부터 그룹번호 시작
    static int[] dirx = {-1, 1, 0, 0};
    static int[] diry = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        group = new int[N][M];
        groupSize = new int[N * M + 2];
        for (int i = 0; i<N; i++){
            String s = br.readLine();
            for (int j = 0; j<M; j++){
                arr[i][j] = s.charAt(j) - '0';
            }
        }//arr입력
        for (int i = 0; i<N; i++){
            for (int j = 0; j<M; j++){
                if (arr[i][j]==0 && group[i][j]==0){
                    bfs(i, j);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        // 벽 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) {
                    sb.append(calc(i, j));
                } else {
                    sb.append(0);
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
    static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        group[x][y] = groupNum;
        int count = 1;
        queue.add(new int[] {x, y});
        while(!queue.isEmpty()){
            int[] xy = queue.poll();
            int curx = xy[0], cury = xy[1];
            for (int i = 0; i<4; i++){
                if (curx+dirx[i] >=0 && curx+dirx[i] < N && cury + diry[i] >=0 && cury+diry[i] <M){
                    if (arr[curx+dirx[i]][cury+diry[i]]==0 && group[curx+dirx[i]][cury+diry[i]]==0){
                        group[curx+dirx[i]][cury+diry[i]] = groupNum;
                        queue.add(new int[] {curx+dirx[i], cury+diry[i]});
                        count++;
                    }
                }
            }
        }
        groupSize[groupNum] = count;
        groupNum++;
    }
    static int calc(int x, int y){
        Set<Integer> set = new HashSet<>();
        int sum = 1; // 자기 자신 포함
        for (int i = 0; i<4; i++){
            if (x+dirx[i] >=0 && x+dirx[i] < N && y + diry[i] >=0 && y+diry[i] <M){
                int g = group[x+dirx[i]][y+diry[i]];
                if (g>0 && !set.contains(g)){
                    sum+=groupSize[g];
                    set.add(g);
                }
            }
        }
        return sum%10;
    }
}
