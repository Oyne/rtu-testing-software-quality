package helper;

import java.util.Random;

public class NumberGenerator {
    private static final int min = -10000;
    private static final int max = 10000;
    private static final Random random = new Random();

    static public int generateInt() {
        return random.nextInt(min, max + 1);
    }

    static public int generateInt(int min, int max) {
        return random.nextInt(min, max + 1);
    }

    static public int generateNonZeroInt() {
        int r = 0;
        while (r == 0) {
            r = random.nextInt(min, max + 1);
        }
        return r;
    }
}