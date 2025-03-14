package DFS입문.EIZI.W1.백준2210숫자판점프;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static Set<String> nums = new HashSet<>(); // 중복 저장 방지
    static int[] dirx = {-1, 1, 0, 0};
    static int[] diry = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[5][5];
        for (int i = 0; i<5; i++){
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            for (int j  = 0; j<5; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }// 숫자판 입력
        for (int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                dfs(i, j, String.valueOf(arr[i][j])); // 해당 좌표 값 String 변환
            }
        }//모든좌표에서 DFS 실행
        System.out.println(nums.size());
    }
    static void dfs(int x, int y, String num){
        if (num.length() == 5){
            nums.add(num);
            return;
        }
        for (int i = 0; i<4; i++){
            int curx = x+dirx[i];
            int cury = x+diry[i];
            if (curx>=0 && curx<arr.length && cury>=0 && cury<arr.length){
                dfs(curx, cury, num+arr[curx][cury]);
            }
        }
    }
}