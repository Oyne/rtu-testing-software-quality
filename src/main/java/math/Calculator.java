package math;

public class Calculator {
    static public int add(int a, int b) {
        return a + b;
    }

    static public int subtract(int a, int b) {
        return a - b;
    }

    static public int multiply(int a, int b) {
        return a * b;
    }

    static public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        return a / b;
    }

    static public int fibonacci(int n) {
        int a = 0, b = 1, c;

        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n == 0) return a;

        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return b;
    }

    static public int lucas(int n) {
        int a = 2, b = 1, c;

        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n == 0) return a;

        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return b;
    }
}
