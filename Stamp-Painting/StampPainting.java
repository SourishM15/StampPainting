import java.util.Scanner;

public class StampPainting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            char[][] desiredPainting = new char[N][N];

            // Read Bessie's desired stamp painting
            for (int i = 0; i < N; i++) {
                desiredPainting[i] = scanner.next().toCharArray();
            }

            int K = scanner.nextInt();
            char[][] stamp = new char[K][K];

            // Read Farmer John's stamp
            for (int i = 0; i < K; i++) {
                stamp[i] = scanner.next().toCharArray();
            }

            String result = canCreateStampPainting(N, desiredPainting, K, stamp);
            System.out.println(result);

            // Skip the newline after each test case except the last one
            if (t < T - 1) {
                scanner.nextLine(); // consume the newline
            }
        }
        scanner.close();
    }

    private static String canCreateStampPainting(int N, char[][] desiredPainting, int K, char[][] stamp) {
        for (int rotation = 0; rotation < 4; rotation++) {
            for (int i = 0; i <= N - K; i++) {
                for (int j = 0; j <= N - K; j++) {
                    boolean match = true;
                    for (int x = 0; x < K; x++) {
                        for (int y = 0; y < K; y++) {
                            if (stamp[x][y] == '*' && desiredPainting[i + x][j + y] != '*') {
                                match = false;
                                break;
                            }
                        }
                        if (!match) {
                            break;
                        }
                    }
                    if (match) {
                        return "YES";
                    }
                }
            }
            rotateClockwise(stamp);
        }
        return "NO";
    }

    private static void rotateClockwise(char[][] matrix) {
        int N = matrix.length;
        char[][] result = new char[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[j][N - 1 - i] = matrix[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            System.arraycopy(result[i], 0, matrix[i], 0, N);
        }
    }
}