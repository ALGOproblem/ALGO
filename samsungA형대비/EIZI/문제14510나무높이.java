package ALGO.samsungA형대비.EIZI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 문제14510나무높이 {
    static int[] arr;
    static Integer[] arr_height;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/문제14510나무높이.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            arr = new int[N];
            arr_height = new Integer[N];
            int max_height = -1;
            int sum = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                max_height = Math.max(arr[i], max_height);
            }

            for (int i = 0; i < N; i++) {
                arr_height[i] = max_height - arr[i];
                sum += arr_height[i];
            }


            // 큰 차이부터 정렬
            Arrays.sort(arr_height, Collections.reverseOrder());

            int x = sum * 2 / 3; // 근사값 설정
            boolean find = false;
            int answer = 0;

            // 모든 나무가 이미 동일한 높이면 0 출력
            if (sum == 0) {
            	System.out.println("#" + test_case + " " + 0);
            	continue;
            }
            
            // sum == 1인 경우 별도로 처리
            if (sum == 1) {
            	System.out.println("#" + test_case + " " + 1);
            	find = true;
            	continue;
            }
            // sum == 2인 경우 별도로 처리
            if (sum == 2) {
            	System.out.println("#" + test_case + " " + 2);
            	find = true;
            	continue;
            }
            // x부터 x*2까지 시뮬레이션
            // 왜 sum이 1이랑 2일 때 예외가 발생하는가...
            for (int i = x; i < x * 2; i++) {
                if (find) break;

                int count1 = (i % 2 == 0) ? i / 2 : i / 2 + 1;
                int count2 = i / 2;

                // **배열을 복사해서 시뮬레이션 (원본 데이터 보호)**
                Integer[] tempArr = Arrays.copyOf(arr_height, N);

                // **2씩 줄이기**
                for (int j = 0; j < N; j++) {
                    while (tempArr[j] > 1 && count2 > 0) {
                        tempArr[j] -= 2;
                        count2--;
                    }
                }

                // **1씩 줄이기**
                for (int j = 0; j < N; j++) {
                    while (tempArr[j] > 0 && count1 > 0) {
                        tempArr[j] -= 1;
                        count1--;
                    }
                }

                // 모든 나무가 0이 되었는지 확인
                boolean allZero = true;
                for (int j = 0; j < N; j++) {
                    if (tempArr[j] > 0) {
                        allZero = false;
                        break;
                    }
                }

                if (allZero) {
                    answer = i;
                    find = true;
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}

}
