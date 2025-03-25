
/*
 * 색종이 정사각형 N = 1, 2, 3, 4, 5 (각각 5개씩)
 * 색종이를 크기가 10x10인 종이 위에 붙임 (한 칸 0, 1 적힘) -> 1이라고 적인 칸은 모두 색종이
 * 조건 : 경계 밖 X, 겹쳐도 X, 칸의 경계와 일치, 0이 적힌 칸에는 색종이 X
 */

 /*ALGO
  * 1. 순서대로 읽으면서 1발견하는 순간 defineSize() 호출
    2. defineSize() -> SIZE가 1, 2, 3, 4, 5인지 확인하기 -> int maxSize = Math.max(maxSize, (int size))와 x,y 좌표를 저장함
    3. 탐색이 끝났다면 maxSize 색종이를 ( isMatch() 호출) 전부 2로 바꾼 뒤, 다시 반복하기
    4. isMatch() -> 색종이를 가장 큰 것부터 사용해서 지금 현재 가지고 있는 색종이로 덮을 수 있는지 확인 -> paper[] 감소시키기
    -> greedy 방식이라 최적해의 보장이 어려움 (일부 예외 케이스 존재)
            A. 그리디 : 매 순간 가장 좋아보이는 선택 (최대, 최소, 최적) -> but 전체적으로 최적해가 된다는 보장은 없음
            B. 백트래킹 : 전부 탐색하고 최적만 고름

    static void solution() {
        while (true) {
            int maxSize = 0, maxX = -1, maxY = -1;
            boolean found = false;
            for (int i = 0; i < 10; i++) { // 1 찾으면서 가장 큰 사이즈 찾기
                for (int j = 0; j < 10; j++) {
                    if (arr[i][j] == 1) {
                        int size = findMaxSize(i, j);
                        if (size > maxSize) {    maxSize = size;    maxX = i;    maxY = j;       }
                        found = true;
                    }
                }
            }
            if (!found) break; // 더 이상 덮을 1이 없으면 끝
            if (maxSize == 0) {  result = -1;  return; } // 붙일 수 없으면 종료
            if (paper[maxSize] > 0) { // 붙이기
                attach(maxX, maxY, maxSize);
                paper[maxSize]--;
                result++;
            } else {  result = -1;  return;  }  // 재고 없음
        }
    }
    static int findMaxSize(int x, int y) { // 이 좌표에서 최대 몇까지 붙일 수 있는지 찾기
        int maxSize = 0;
        for (int size = 1; size <= 5; size++) {
            if (canAttach(x, y, size)) maxSize = size;
            else break;
        }
        return maxSize;
    }
    static boolean canAttach(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != 1) return false;
            }
        }
        return true;
    }
    
    GPT said "maxSize 찾아서 붙이는 게 아니라 재귀적으로 매번 가장 큰 사이즈부터 시도하고 실패하면 줄이는 방식"
    1. 10x10 탐색하면서 첫번째 1을 찾음
    2. 찾은 좌표에서 5x5부터 1x1까지 덮을 수 있는지 확인
    3. 덮을 수 있고 색종이가 남았다면 붙인 다음 재귀 호출
    4. 재귀가 끝나면 붙인 것을 다시 원상 복구 (백 트래킹)
    5. 모든 1을 덮었으면 현재까지 쓴 색종이 개수로 최소값 갱신
    6. 만약 색종이 다 써서 못 덮으면 해당 경로는 실패 처리리
  */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준17136색종이붙이기 {
    static int[][] arr;
    static int[] paper;
    static int min;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            arr = new int[10][10];
            paper = new int[]{0, 5, 5, 5, 5, 5}; // 색종이 재고고
            min = Integer.MAX_VALUE;
            for (int i = 0; i<10; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j<10; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }//arr입력
            dfs(0, 0, 0);
            System.out.println(min == Integer.MAX_VALUE ? -1 : min);

        }

    static boolean canAttach(int x, int y, int size){ // 색종이 붙일 수 있는지 test
        if (x+size>10 || y+size>10) return false;
        for (int i = x; i<x+size; i++){
            for (int j = y; j<y+size; j++){
                if(arr[i][j]!=1) return false;
            }
        }
        return true;
    }
    static void attach(int x, int y, int size, int val){
        for (int i = x; i<x+size; i++){
            for (int j = y; j<y+size; j++){
                arr[i][j] = val; // val = 0 (붙임) = 1(뗌)
            }
        }
    }
    static void dfs(int x, int y, int used){
        if(used>=min) return;
        if (x>=10){  min = Math.min(min, used); return;  }
        if (y>=10){// 다음 줄로 이동
            dfs(x+1, 0, used);
            return;
        }
        if (arr[x][y] ==1){ // 1발견
            for (int size = 5; size>=1; size--){
                if (paper[size]>0 && canAttach(x, y, size)){
                    attach(x, y, size, 0);//색종이 붙이기
                    paper[size]--;
                    dfs(x, y+1, used+1); // 다음칸 재귀
                    attach(x, y, size, 1); // 원상복구
                    paper[size]++;

                }
            }

        } else {
            dfs(x, y+1, used); // 다음 칸으로 넘어감
        }
    }
    
}
