package DFS입문.MIRIM.solved.judgeTheConcave_11315;

import java.util.Scanner;

public class JudgeTheConcave_11315 {
    static int[][] arr;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();

            arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                String str = sc.next();
                for (int j = 0; j < N; j++) {
                    if (str.charAt(j) == '.')
                        arr[i][j] = 0;
                    else
                        arr[i][j] = 1;
                }
            }//입력

            boolean answer = false;
            for (int i = 0; i < N; i++) {
                int countRow = 0;
                int countColumn = 0;

                for (int j = 0; j < N; j++) {
                    // 행
                    if (arr[i][j] == 1) countRow++;
                    else countRow = 0;

                    if (countRow == 5) {
                        answer = true;
                        break;
                    }
                    // 열
                    if (arr[j][i] == 1) countColumn++;
                    else countColumn = 0;

                    if (countColumn == 5) {
                        answer = true;
                        break;
                    }

                    // 대각선: 왼쪽 상단 -> 오른쪽 하단
                    int countDiagonal1 = 0;
                    if (arr[i][j] == 1 && i + 4 < N && j + 4 < N) {
                        for(int k = 0; k < 5; k++) {
                            if(arr[i + k][j + k] == 1) countDiagonal1++;
                            else break;
                        }

                        if (countDiagonal1 == 5)  {
                            answer = true;
                            break;
                        }
                    }

                    // 대각선: 오른쪽 상단 -> 왼쪽 하단
                    int countDiagonal2 = 0;
                    if (arr[i][j] == 1 && i + 4 < N && j - 4 >= 0) {
                        for(int k = 0; k < 5; k++) {
                            if(arr[i + k][j - k] == 1) countDiagonal2++;
                            else break;
                        }

                        if (countDiagonal2 == 5) {
                            answer = true;
                            break;
                        }
                    }

                }// 검사 j for문
                if (answer) break;
            }// 검사 for문

            String strike = "NO";
            if (answer) {
                strike = "YES";
            }

            System.out.printf("#%d %s\n", tc, strike);
        }// tc
        sc.close();
    }// main

}
