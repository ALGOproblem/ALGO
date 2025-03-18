package week4;

import java.io.*;
import java.util.*;

/* 알고리즘
 * 1. catHairs[] 고양이집에 떨어진 털, cats[] 고양이가 가지고 있는 털
 * 2. matchCats()
 * 		if (idx==catSize) answer++; return; // 종료 조건
 * 		for문 안에서 cats[i].contains(matchHair) && !visited
 * 	
 * 
 * 틀린 이유
 * 1. 오늘 잘 안 풀리는 날..
 * 2. static ArrayList<Character>[] cats = new ArrayList[catSize];가 순간적으로 안 떠오름
 * 3. catHairs = br.readLine().replaceAll(" ", "").toCharArray(); // 공백 제거 후 변환
 * 		toCharArray()를 하면 공백도 문자배열에 포함되는데 생각을 못함
 * 
 */
public class 과목평가3건물주고양이 {
    static int catSize;
    static char[] catHairs;
    static ArrayList<Character>[] cats;
    static boolean[] visitedCat;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int test = 1; test <= T; test++) {
            // 초기화
            catSize = Integer.parseInt(br.readLine()); // 고양이 수 = 집의 수
            catHairs = br.readLine().replaceAll(" ", "").toCharArray(); // 공백 제거 후 변환

            cats = new ArrayList[catSize];
            for (int i = 0; i < catSize; i++) {
                cats[i] = new ArrayList<>();
            }
            visitedCat = new boolean[catSize]; // boolean[] 사용 (자동 false 초기화)
            answer = 0;

            // 고양이 정보 입력
            for (int i = 0; i < catSize; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int time = Integer.parseInt(st.nextToken());
                for (int j = 0; j < time; j++) {
                    cats[i].add(st.nextToken().charAt(0));
                }
            }

            // 백트래킹 실행
            matchCat(0);
            System.out.println("#" + test + " " + answer);
        }
    }

    public static void matchCat(int idx) {
        if (idx == catSize) {  // 모든 집을 배정했을 때 정답 카운트 증가
            answer++;
            return;
        }

        char matchHair = catHairs[idx]; // 현재 집의 털 색깔

        for (int i = 0; i < catSize; i++) {
            if (cats[i].contains(matchHair) && !visitedCat[i]) { // 해당 털을 가진 고양이 & 아직 배정되지 않은 경우
                visitedCat[i] = true; // 방문 체크
                matchCat(idx + 1); // 다음 집으로 이동
                visitedCat[i] = false; // 백트래킹 (되돌리기)
            }
        }
    }
}
