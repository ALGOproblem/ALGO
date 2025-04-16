package 실습.MIRIM.APS응용_Day06_07_그래프탐색;

import java.util.*;

public class swea_8275_햄스터 {
    static int N, X, M;
    static List<Record> records;
    static int[] maxArr;
    static int maxHam;

    static class Record {
        int l, r, s;

        public Record(int l, int r, int s) {
            this.l = l;
            this.r = r;
            this.s = s;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt(); // 우리 개수
            X = sc.nextInt(); // 한 우리 당 햄스터 최댓값
            M = sc.nextInt(); // 기록 수

            records = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                records.add(new Record(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }

            maxArr = null;
            maxHam = -1;
            dfs(0, new int[N]);

            if (maxHam == -1) {
                System.out.printf("#%d %d\n", tc, -1);
            } else {
                System.out.printf("#%d ", tc);
                for (int i = 0; i < N; i++) {
                    System.out.print(maxArr[i] + " ");
                }
                System.out.println();
            }

        }//tc
        sc.close();
    }//main

    static void dfs(int idx, int[] cages) {
        if (idx == N) {
            if (isValid(cages)) {
                int tmp = 0;
                for (int i = 0; i < N; i++) {
                    tmp += cages[i];
                }
                if (tmp > maxHam || (tmp == maxHam && isSmaller(maxArr, cages))) {
                    maxArr = cages.clone();
                    maxHam = tmp;
                }
            }
            return;
        } // 종료 조건

        for (int i = X; i >= 0; i--) {
            cages[idx] = i;
            dfs(idx + 1, cages);
        }
    } // dfs

    // 기록 조건을 모두 만족한 배열인지 확인
    static boolean isValid(int[] cages) {
        for (Record record : records) {
            int temp = 0;
            for (int i = record.l - 1; i < record.r; i++) {
                temp += cages[i];
            }
            if (temp != record.s) return false;
        }
        return true;
    }

    static boolean isSmaller(int[] pre, int[] cur) {
        if (pre == null) return true;
        for (int i = 0; i < N; i++) {
            if (pre[i] < cur[i]) return false;
            else if (pre[i] > cur[i]) return true;
        }
        return false;
    }
}
