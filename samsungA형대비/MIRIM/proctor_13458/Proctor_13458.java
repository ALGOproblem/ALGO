package samsungA형대비.MIRIM.proctor_13458;

import java.util.Scanner;

public class Proctor_13458 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 시험장의 개수
        int[] test = new int[N];
        for (int i = 0; i < N; i++) {
            test[i] = sc.nextInt();
        }
        int B = sc.nextInt(); // 총감독관이 볼 수 있는 수
        int C = sc.nextInt(); // 부감독관이 볼 수 있는 수

        // 필요한 감독관(총+부) 수의 최솟값
        long proctors = N;
        for (int i = 0; i < N; i++) {
            int student = test[i] - B;

            if (student > 0) {
                proctors += (student / C);
                if (student % C != 0)
                    proctors++;
            }
        }
        System.out.println(proctors);
    }
}
