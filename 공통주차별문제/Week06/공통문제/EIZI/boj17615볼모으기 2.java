import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine().trim();

        // 전체 R, B 개수 구하기
        int totalR = 0, totalB = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'R') totalR++;
            else if (c == 'B') totalB++;
        }
        if (totalR == 0 || totalB == 0) {
            System.out.println(0);
            return;
        }

        // 1. 'R'을 왼쪽으로 모으는 경우:
        // 왼쪽부터 연속된 'R'의 개수
        int leftR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                leftR++;
            } else {
                break;
            }
        }
        int movesRLeft = totalR - leftR;  // 모여있는 왼쪽 'R' 이외의 R 개수

        // 2. 'R'을 오른쪽으로 모으는 경우:
        int rightR = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'R') {
                rightR++;
            } else {
                break;
            }
        }
        int movesRRight = totalR - rightR;  // 모여있는 오른쪽 'R' 이외의 R 개수

        // 3. 'B'를 왼쪽으로 모으는 경우:
        int leftB = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'B') {
                leftB++;
            } else {
                break;
            }
        }
        int movesBLeft = totalB - leftB;  // 모여있는 왼쪽 'B' 이외의 B 개수

        // 4. 'B'를 오른쪽으로 모으는 경우:
        int rightB = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'B') {
                rightB++;
            } else {
                break;
            }
        }
        int movesBRight = totalB - rightB;  // 모여있는 오른쪽 'B' 이외의 B 개수
        int answer = Math.min(Math.min(movesRLeft, movesRRight), Math.min(movesBLeft, movesBRight));
        System.out.println(answer);
    }
}
