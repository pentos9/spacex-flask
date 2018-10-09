package com.spacex.concurrent.mis.pattern.func;

import java.util.Random;

public class ConditionCollections {

    private static final Random random = new Random();

    public static boolean isGunSilent(Context context) {
        return random.nextBoolean();
    }

    public static boolean isGunSoundBig(Context context) {
        return random.nextBoolean();
    }

    public static boolean isShootingBirdLegal(Context context) {
        return random.nextBoolean();
    }
}
