package 실습.김미림.APS응용_Day06_07_그래프탐색;

import java.util.*;

public class swea_1952_수영장 {
    static int[] price;
    static int[] months;
    static int minPrice;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            price = new int[4];
            for (int i = 0; i < 4; i++) {
                price[i] = sc.nextInt();
            }// 이용권 가격들

            months = new int[12];
            for (int i = 0; i < 12; i++) {
                months[i] = sc.nextInt();
            }

            minPrice = price[3]; // 1년 이용권으로 시작함
            dfs(0, 0);

            System.out.printf("#%d %d\n", tc, minPrice);

        }// tc
        sc.close();
    }//main

    static void dfs(int idx, int sum) {
        if (idx >= 12) {
            minPrice = Math.min(minPrice, sum);
            return;
        } // 종료 조건: 12월까지 모두 계산 시 최솟값 비교 후 종료

        if (months[idx] == 0)  {
            dfs(idx + 1, sum);
            return;
        } // 이미 개월 이용권이 있으면 통과

        // 일일 이용권 -> 다음 달
        dfs(idx + 1, sum + months[idx] * price[0]);

        // 1달 이용권 -> 다음 달
        dfs(idx + 1, sum + price[1]);

        // 3달 이용권
        dfs(idx + 3, sum + price[2]);

    }
}
