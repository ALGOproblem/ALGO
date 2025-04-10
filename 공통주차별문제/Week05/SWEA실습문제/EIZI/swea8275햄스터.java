

import java.io.*;
import java.util.*;

public class swea8275햄스터 {
    static int N, X, M;
    static int[] arr;
    static int[][] reports;
    static List<int[]> validCandidates;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/swea8275햄스터.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new int[N];
            validCandidates = new ArrayList<>();
            reports = new int[M][3];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                reports[i][0] = Integer.parseInt(st.nextToken());
                reports[i][1] = Integer.parseInt(st.nextToken());
                reports[i][2] = Integer.parseInt(st.nextToken());
            }

            backTracking(0);
            printBestCandidate(test);
        }
    }

    static void backTracking(int depth) {
        if (depth == N) {
            if (isValid()) validCandidates.add(arr.clone());
            return;
        }
        for (int i = X; i >= 0; i--) {
            arr[depth] = i;
            backTracking(depth + 1);
        }
    }

    static boolean isValid() {
        for (int[] report : reports) {
            int start = report[0] - 1;
            int end = report[1];
            int expected = report[2];
            int sum = 0;
            for (int i = start; i < end; i++) sum += arr[i];
            if (sum != expected) return false;
        }
        return true;
    }

    static void printBestCandidate(int test) {
        if (validCandidates.isEmpty()) {
            System.out.println("#" + test + " -1");
            return;
        }

        int maxSum = -1;
        List<int[]> bestList = new ArrayList<>();

        for (int[] candidate : validCandidates) {
            int sum = Arrays.stream(candidate).sum();
            if (sum > maxSum) {
                maxSum = sum;
                bestList.clear();
                bestList.add(candidate);
            } else if (sum == maxSum) {
                bestList.add(candidate);
            }
        }

        int[] answer = bestList.get(bestList.size() - 1);  // 사전적으로 가장 큰 것
        StringBuilder sb = new StringBuilder("#" + test + " ");
        for (int num : answer) sb.append(num).append(" ");
        System.out.println(sb);
    }
}
