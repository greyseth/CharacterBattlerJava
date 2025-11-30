package util;

import java.util.Random;

public class RandomChar {
    private static final String[] FIRST_NAMES = {
            "Alex", "Jordan", "Taylor", "Morgan", "Riley",
            "Sam", "Casey", "Jamie", "Avery", "Quinn"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Davis",
            "Miller", "Wilson", "Moore", "Taylor", "Anderson"
    };

    private static final Random RANDOM = new Random();

    public static String makeName() {
        String first = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
        String last = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
        return first + " " + last;
    }

    public static int[] makeStats() {
        int[] stats = {0, 0, 0};

        stats[0] = RANDOM.nextInt(1, 12+1);
        stats[1] = RANDOM.nextInt(1, (12-(stats[0]==1?0:stats[0]))+1);
        stats[2] = 12 - (stats[0]+stats[1]);

        return stats;
    }
}
