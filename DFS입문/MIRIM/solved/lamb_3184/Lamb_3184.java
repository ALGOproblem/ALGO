package DFS입문.MIRIM.solved.lamb_3184;

import java.util.Scanner;

public class Lamb_3184 {
    static int R;
    static int C;
    static String[][] map;
    static int lamb;
    static int wolf;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();

        map = new String[R][];
        for (int i = 0; i < R; i++) {
            String str = sc.nextLine();
            map[i] = str.split("");
        } // 입력

        int lambSum = 0;
        int wolfSum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                lamb = 0;
                wolf = 0;
                dfs(i, j);

                if (lamb > wolf)
                    wolf = 0;
                else
                    lamb = 0;

                lambSum += lamb;
                wolfSum += wolf;
            }
        } // 탐색

        System.out.println(lambSum + " " + wolfSum);

    }// main

    static void dfs(int r, int c) {
        String curr = map[r][c];
        if (curr.equals("#"))
            return;
        else if (curr.equals("o"))
            lamb++;
        else if (curr.equals("v"))
            wolf++;

        map[r][c] = "#";
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                continue;
            dfs(nr, nc);
        }

    }// dfs
}
