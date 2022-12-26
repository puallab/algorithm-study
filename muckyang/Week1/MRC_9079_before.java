import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MRC_9079_before {

    static int T;
    static boolean[][] map;
    static boolean[] caseMap;
    static Map<Integer, Integer> answerBitMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        map = new boolean[T * 3][3];
        caseMap = new boolean[9];

        answerBitMap = new HashMap<>();
        allCase(0, 0, 0);



        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            int checkBit = 0;
            for (int j = 0; j < 3; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 3; k++) {
                    if (st.nextToken().equals("H")) {
                        checkBit = checkBit | 1 << (j * 3 + k);
                    }
                }
            }

            Integer answer = answerBitMap.get(checkBit);

            if (answer != null) {
                sb.append(answer).append("\n");
            } else {
                sb.append("-1").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void allCase(int bit, int cnt, int flag) {
        if (answerBitMap.get(bit) == null
                || answerBitMap.get(bit) > cnt) {
            answerBitMap.put(bit, cnt);
        }

        for (int i = 0; i < 6; i++) {
            if ((flag & 1 << i) == 0) {
                go(i);
                int nextBit = 0;
                for (int b = 0; b < 9; b++) {
                    if (caseMap[b])
                        nextBit = nextBit | (1 << b);
                }

                allCase(nextBit, cnt + 1, flag | 1 << i);
                go(i);
            }
        }
    }

    private static void go(int line) {
        if (line == 0) {
            caseMap[0] = !caseMap[0];
            caseMap[1] = !caseMap[1];
            caseMap[2] = !caseMap[2];
        } else if (line == 1) {
            caseMap[3] = !caseMap[3];
            caseMap[4] = !caseMap[4];
            caseMap[5] = !caseMap[5];
        } else if (line == 2) {
            caseMap[6] = !caseMap[6];
            caseMap[7] = !caseMap[7];
            caseMap[8] = !caseMap[8];
        } else if (line == 3) {
            caseMap[0] = !caseMap[0];
            caseMap[3] = !caseMap[3];
            caseMap[6] = !caseMap[6];
        } else if (line == 4) {
            caseMap[1] = !caseMap[1];
            caseMap[4] = !caseMap[4];
            caseMap[7] = !caseMap[7];
        } else if (line == 5) {
            caseMap[2] = !caseMap[2];
            caseMap[5] = !caseMap[5];
            caseMap[8] = !caseMap[8];
        }
    }
}
