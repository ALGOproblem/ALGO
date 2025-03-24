import java.util.*;

public class 백준16496_벽부수고이동하기4 {
    static int N, M;
    static int[][] map;
    static int[][] result;
    static int[][] group; // 0의 그룹번호 저장하기
    static int groupIdx = 1;
    static Map<Integer, Integer> groupInfo = new HashMap<>(); // 그룹인덱스, 그룹에 있는 0의 개수

    static  int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }// 입력

        group = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && group[i][j] == 0)
                    groupBFS(i, j);
            }
        } // 그룹화

        result = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    Set<Integer> nearGroups = new HashSet<>();
                    int sum = 1;

                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 1) continue;

                        int gid = group[nr][nc];
                        if (nearGroups.add(gid)) {
                            sum += groupInfo.get(gid);
                        }
                    }

                    result[i][j] = sum % 10;
                } // 1일 때 그룹 값 더하기

                System.out.print(result[i][j]);
            }

            System.out.println();
        } // 1일때 값 구하기

    }// main

    // 0에 대해서 근처 0들을 그룹으로 묶기 like 애니팡...
    // 0일때, 그룹 배정이 안되어 있을때
    static void groupBFS(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c}); // 행, 열
        group[r][c] = groupIdx;
        int size = 1;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = curR + dr[i];
                int nc = curC + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M ) continue;

                if (map[nr][nc] == 0 && group[nr][nc] == 0) {
                    queue.offer(new int[]{nr, nc});
                    group[nr][nc] = groupIdx;
                    size++;
                }
            }// delta
        }// while

        groupInfo.put(groupIdx++, size);
    }// bfs
}
