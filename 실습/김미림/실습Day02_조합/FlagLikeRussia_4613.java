package 실습.김미림.실습Day02_조합;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlagLikeRussia_4613 {
    static char[] color = { 'W', 'B', 'R' };
    static int N;
    static int[][] line;
    static List<int[]> result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            int M = sc.nextInt();

            line = new int[N][3]; // 줄 별 색상 개수 저장

            for (int i = 0; i < N; i++) {
                int w = 0;
                int b = 0;
                int r = 0;

                String str = sc.next();

                for (int j = 0; j < M; j++) {
                    switch (str.charAt(j)) {
                        case 'W':
                            w++;
                            break;
                        case 'B':
                            b++;
                            break;
                        case 'R':
                            r++;
                            break;
                    }
                }
                line[i] = new int[] { w, b, r };

            } // 입력 : 색상 개수 저장

            result = new ArrayList<>();

            combDeg(0, new ArrayList<Character>()); // 중복 조합

            int answer = Integer.MAX_VALUE;

            for (int[] arr : result) {
                int sum = 0;

                for (int i = 0; i < N; i++) {
                    if (i < arr[0]) {
                        sum += line[i][1] + line[i][2];
                    } else if (i < arr[0] + arr[1]) {
                        sum += line[i][0] + line[i][2];
                    } else {
                        sum += line[i][0] + line[i][1];
                    }
                }
                answer = Math.min(answer, sum);
            } //최솟값 찾기

            System.out.printf("#%d %d\n", tc, answer);

        } // tc
        sc.close();
    }// main

    // 결과: W, B, R의 개수 중복 조합(1 이상의 자연수일때)
    static void combDeg(int start, List<Character> current) {
        if (current.size() == N - 3) { // 각각 1개씩
            int w = 1, b = 1, r = 1;

            for (char color : current) {
                switch (color) {
                    case 'W':
                        w++;
                        break;
                    case 'B':
                        b++;
                        break;
                    case 'R':
                        r++;
                        break;
                }
            }

            result.add(new int[] {w, b, r});
            return;
        }

        for (int i = start; i < 3; i++) {
            current.add(color[i]);
            combDeg(i, current);
            current.remove(current.size() - 1);
        }

    }// 중복 조합

}
