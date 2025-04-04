package 공통주차별문제.Week06.SWEA실습문제.Terry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_17615_볼모으기 {
    static BufferedReader input;

    // N^2 로 풀 경우 시간 초과 -> 500_000 ^ 2 = 250_000_000_000
    // N으로 풀기

    // 풀이 방법
    // 0. R,B를 왼쪽으로 혹은 오른쪽으로 보낸다.
    // 1. R을 왼쪽으로 보낼 경우 왼쪽에서 B를 찾는다.
    // 2. B를 찾은 후 R을 발견할 경우 cnt++ 한다.
    // 3. 1~2를 총 4번한다.

    // 추가 설명
    // - 오른쪽에서 R을 찾을 경우
    // + 오른쪽부터 배열을 순회한다.
    // + 오른쪽에서 B를 찾는다.
    // + 이후에 R을 찾으면 cnt++
    public static void main(String[] args) throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in));
        input = new BufferedReader(new FileReader("input.txt"));

        int N = Integer.parseInt(input.readLine());

        char[] line = input.readLine().toCharArray();

        char[] colors = new char[] { 'R', 'B' };

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 2; i++) {
            char color = colors[i];

            // 1. R을 왼쪽으로 // B를 왼쪽으로
            int leftCnt = 0;
            boolean leftFlag = false;
            for (int j = 0; j < N; j++) {
                // 다른 색일 경우 flag를 true
                if (line[j] != color && !leftFlag) {
                    leftFlag = true;
                    continue;
                }
                // 같은 색이고 플래그가 있을 경우 cnt++
                if (line[j] == color && leftFlag) {
                    leftCnt++;
                }
            }
            // 2. R을 오른쪽으로 // B를 오른쪽으로
            int rightCnt = 0;
            boolean rightFlag = false;
            for (int j = N - 1; j >= 0; j--) {
                if (line[j] != color && !rightFlag) {
                    rightFlag = true;
                    continue;
                }

                if (line[j] == color && rightFlag) {
                    rightCnt++;
                }
            }
            int minByColor = Math.min(leftCnt, rightCnt);

            min = Math.min(min, minByColor);
        }

        System.out.println(min);
    }
}
