package 실습.이지민.실습Day01_부분집합;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/*
 * A1, A2, ... , AN의 N개의 자연수가 주어졌을 때, 최소 1개 이상의 수를 선택하여 그 합이 K가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 */
 
public class 문제2817부분수열의합 {
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken()); // 1<=N<=20 선택할 수 있는 자연수
            int K = Integer.parseInt(st.nextToken()); // 1<=K<=1000 만들어야 하는 수
            int[] arr = new int[N];
            s = br.readLine();
            st = new StringTokenizer(s);
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken()); // 1<=K<=1000 만들어야 하는 수
                arr[i] = num;
            } // 입력 받기
            int answer = 0;
            for (int i = 0; i < (1 << N); i++) { // Math.pow(2, N); 경우의 수
                int sum = 0;
                for (int j = 0; j < N; j++) { // 입력 수 만큼
                    if ((i & (1 << j)) > 0) {
                        sum+= arr[j];
                    }
                }
                if (sum==K) answer++;
            }
            System.out.println("#"+test_case+" "+answer);
 
        }
 
    }
 
}