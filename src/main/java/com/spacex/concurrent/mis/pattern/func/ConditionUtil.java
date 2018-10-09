package com.spacex.concurrent.mis.pattern.func;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ConditionUtil {

    private static final List<Function<Context, Boolean>> conditions = new ArrayList<>();

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        init();
        AtomicInteger counter = new AtomicInteger(0);
        int total = 1000;
        for (int i = 0; i < total; i++) {
            Boolean result = judge(new Context(), conditions);
            if (result) {
                counter.incrementAndGet();
                System.out.println(String.format("[Hit] %s,%s", result, i));
            }
        }

        System.out.println(String.format("[Counter] %s,percentage:%s", counter.get(), (counter.get() / 1000.0F)));
    }

    public static void init() {
        conditions.add(ConditionCollections::isGunSilent);
        conditions.add(ConditionCollections::isGunSoundBig);
        conditions.add(ConditionCollections::isShootingBirdLegal);
    }

    public static boolean judge(Context context, List<Function<Context, Boolean>> conditions) {
        for (Function<Context, Boolean> condition : conditions) {
            if (!condition.apply(context)) {
                return false;
            }
        }

        return true;
    }
}
