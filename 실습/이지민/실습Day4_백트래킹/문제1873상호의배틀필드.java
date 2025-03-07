package 실습Day4_백트래킹;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/* 게임 맵 결과 프린트
 * 1. 전차의 위치는 static하게 관리한 다음 마지막에 추가
 * 2. case '^': case 'U': car_dir = 0; break; -> 하나하나 쓰다가 GPT가 이렇게 써도 된다고 함
 * 3. move() 범위 벗어났나? -> '.'인가? -> 움직임
 * 4. shoot() 범위 벗어났나? -> '#' 강철벽 만나면 종료 '*' 벽 만나면 부수기
 * 
 */

public class 문제1873상호의배틀필드 {
    static char[][] board;
    static int H, W;
    static int car_dir;
    static int[] car_xy;
    static int[] dirx = {-1, 1, 0, 0}; // 상하좌우
    static int[] diry = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/문제1873상호의배틀필드.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            board = new char[H][W];
            car_xy = new int[2];

            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for (int j = 0; j < W; j++) {
                    board[i][j] = s.charAt(j);
                    if (board[i][j] == '^' || board[i][j] == 'v' || board[i][j] == '<' || board[i][j] == '>') {
                        changeCarDir(board[i][j]);
                        car_xy[0] = i;
                        car_xy[1] = j;
                        board[i][j] = '.'; // 전차의 위치는 static으로 가지고 다니다가 마지막에 호출
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String commands = br.readLine();
            for (char cmd : commands.toCharArray()) {
                game(cmd);
            }

            switch (car_dir) {
                case 0: board[car_xy[0]][car_xy[1]] = '^'; break;
                case 1: board[car_xy[0]][car_xy[1]] = 'v'; break;
                case 2: board[car_xy[0]][car_xy[1]] = '<'; break;
                case 3: board[car_xy[0]][car_xy[1]] = '>'; break;
            }

            System.out.print("#" + tc + " ");
            for (char[] row : board) {
                System.out.println(new String(row));
            }
        }
    }

    static void game(char g) {
        if (g == 'S') {
            shoot();
        } else {
            changeCarDir(g);
            move();
        }
    }

    static void changeCarDir(char c) {
        switch (c) {
            case '^': case 'U': car_dir = 0; break;
            case 'v': case 'D': car_dir = 1; break;
            case '<': case 'L': car_dir = 2; break;
            case '>': case 'R': car_dir = 3; break;
        }
    }

    static void move() {
        int nx = car_xy[0] + dirx[car_dir];
        int ny = car_xy[1] + diry[car_dir];

        if (nx >= 0 && ny >= 0 && nx < H && ny < W && board[nx][ny] == '.') {
            car_xy[0] = nx;
            car_xy[1] = ny;
        }
    }

    static void shoot() {
        int x = car_xy[0], y = car_xy[1];

        while (true) {
            x += dirx[car_dir];
            y += diry[car_dir];

            if (x < 0 || y < 0 || x >= H || y >= W) return;

            if (board[x][y] == '*') {
                board[x][y] = '.';
                return;
            }

            if (board[x][y] == '#') return;
        }
    }
}
