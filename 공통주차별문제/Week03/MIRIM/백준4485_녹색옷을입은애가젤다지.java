package 공통주차별문제.Week03.MIRIM;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 백준4485_녹색옷을입은애가젤다지 {
    static int N;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = 1;

        while (true) {
            N = sc.nextInt();
            if (N == 0) break;

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            } // 입력


            System.out.printf("Problem %d: %d\n", tc, dijkstra(0, 0));
            tc++;
        }// 테스트 케이스
    }// main

    // 다익스트라 사용
    static int dijkstra(int startR, int startC) {
        int[][] dis = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        dis[startR][startC] = map[startR][startC];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[] {startR, startC, dis[startR][startC]});

        int[] dr = new int[] {1, -1, 0, 0};
        int[] dc = new int[] {0, 0, 1, -1};

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int nodeR = node[0];
            int nodeC = node[1];
            int nodeDis = node[2];

            if (nodeR == N - 1 && nodeC == N - 1) break;
            if (nodeDis > dis[nodeR][nodeC]) continue;

            for (int i = 0; i < 4; i++) {
                int nr = nodeR + dr[i];
                int nc = nodeC + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                int newDis = nodeDis + map[nr][nc];

                if (newDis < dis[nr][nc]) {
                    dis[nr][nc] = newDis;
                    pq.offer(new int[] {nr, nc, newDis});
                }
            }
        }// while

        return dis[N - 1][N - 1];
    }

}

