package 공통주차별문제.Week03.MIRIM;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 백준2183_미로탐색 {
    static int N;
    static int M;
    static int[][] map;

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

        System.out.println(dijkstra(0, 0));

    }

    static int dijkstra(int r, int c) {
        int[][] dis = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        dis[r][c] = 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[] {r, c, 1});

        int[] dr = new int[] {1, -1, 0, 0};
        int[] dc = new int[] {0, 0, -1, 1};

        while (!pq.isEmpty()) {

            int [] node = pq.poll();
            int nowR = node[0];
            int nowC = node[1];
            int nodeDis = node[2];

            if (nowR == N - 1 && nowC == M - 1) return nodeDis;

            if (nodeDis > dis[nowR][nowC]) continue;

            for (int i = 0; i < 4; i++) {
                int nr = nowR + dr[i]; // r, c로 하면 초기값에 대해서만 탐색함. 주의
                int nc = nowC + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (map[nr][nc] == 0) continue;

                int newDis = nodeDis + 1;

                if (newDis < dis[nr][nc]) {
                    dis[nr][nc] = newDis;
                    pq.offer(new int[] {nr, nc, newDis});
                }

            } // 이동

        }// 우선순위 큐

        return -1;

    }//dijkstra
}

