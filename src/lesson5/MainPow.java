package lesson5;

public class MainPow {

    public static void main(String[] args) {
        for (long a = 0; a <= 10; a++) {
            for (int n = 0; n <= 10; n++) {
                System.out.print((long)pow(a, n));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static double pow(long a, int n) {
        if (n < 0) return 1. / mathPowerEvenRecursive(a, n);
        else if (n > 0) return mathPowerEvenRecursive(a, n);
        else return 1;
    }

    private static long mathPowerEvenRecursive(long a, int n) {
        if (n == 1) return a;
        if (n % 2 == 0) {
            long q = mathPowerEvenRecursive(a, n / 2);
            return q * q;
        } else return mathPowerEvenRecursive(a, n - 1) * a;
    }
}
