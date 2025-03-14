package 실습.김미림.실습Day01_부분집합;

import java.util.Scanner;

public class Cook_4012 {
    static int N;
    static int[][] synergy;
    static int minDiff;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 목표: 두 음식간 맛의 차이 최소
        // 음식의 맛 = 시너지의 합 -> (i, j) + (j, i)
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            synergy = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    synergy[i][j] = sc.nextInt();
                }
            } // 입력

            // 식재료 뽑기
            minDiff = Integer.MAX_VALUE;
            comb(0, 0, new boolean[N]);

            System.out.printf("#%d %d\n", tc, minDiff);

        } // tc
        sc.close();
    }// main

    // N/2개 뽑기 -> 불린형에 저장
    static void comb(int idx, int fIdx, boolean[] selected) {
        // 종료
        if (fIdx == N / 2) {
            calDiff(selected);
            return;
        }
        if (idx == N) return;

        // 재귀
        selected[idx] = true;
        comb(idx + 1, fIdx + 1, selected); // 담기

        selected[idx] = false;
        comb(idx + 1, fIdx, selected); // 제외
    }

    // 시너지 -> 최소 시너지 차이 계산하기
    static void calDiff(boolean[] selected) {
        int[] A = new int[N / 2];
        int[] B = new int[N / 2];
        int aIdx = 0, bIdx = 0;

        for (int i = 0; i < N; i++) {
            if (selected[i]) A[aIdx++] = i;
            else B[bIdx++] = i;
        }

        int aSynergy = calSynergy(A);
        int bSynergy = calSynergy(B);

        minDiff = Math.min(minDiff, Math.abs(aSynergy - bSynergy));
    }

    // N/2 -> 2개 뽑아 시너지 계산
    static int calSynergy(int[] group) {
        int sum = 0;

        for (int i = 0; i < group.length; i++) {
            for (int j = i + 1; j < group.length; j++) {
                sum += synergy[group[i]][group[j]] + synergy[group[j]][group[i]];
            }
        }
        return sum;
    }
}
