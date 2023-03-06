package Week10_재도전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2632_피자판매 {
    static int size, n, m, N, M, arrA[], arrB[], ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ans = 0;
        arrA = init(n);
        arrB = init(m);

        if (arrA.length < arrB.length) solve(arrA, arrB);
        else solve(arrB, arrA);
        System.out.println(ans);
    }

    private static void solve(int[] A, int[] B) {
        for (int i = 0; i < A.length; i++) {
            if(A[i] > size) return;

            int v = size - A[i];
            ans += upperBound(B, v) - lowerBound(B, v);
        }
    }

    private static int upperBound(int[] arr, int val) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] <= val) l = mid + 1;
            else r = mid;
        }

        return l;
    }

    private static int lowerBound(int[] arr, int val) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] >= val) r = mid;
            else l = mid + 1;
        }

        return l;
    }
    private static int[] init(int n) throws IOException {
        int[] input = new int[2 * n];
        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            input[i] = num;
            input[n + i] = num;
        }
        input[n] = Integer.parseInt(br.readLine());

        for (int i = 1; i < input.length; i++)
            input[i] += input[i - 1];

        int N = n - 1;
        int[] arr = arrayCopy(input, n, n * N + 1);

        int i = n;
        for (int s = 1; s < n; s++) {
            for (int e = s + N; e > s; e--) {
                arr[++i] = input[e] - input[s];
            }
        }

        Arrays.sort(arr);
        return arr;
    }

    private static int[] arrayCopy(int[] arr, int len, int size) {
        int[] tmp = new int[size + 1];
        for (int i = 1; i <= len; i++)
            tmp[i] = arr[i];
        return tmp;
    }
}
