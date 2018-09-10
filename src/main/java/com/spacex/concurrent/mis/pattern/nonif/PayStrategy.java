package com.spacex.concurrent.mis.pattern.nonif;

public class PayStrategy {
    public static float deadAmount() {
        return 200.0F;
    }

    public static float separatedAmount() {
        return 310.0F;
    }

    public static float retiredAmount() {
        return 250.0F;
    }

    public static float normalPayAmount() {
        return 400.0F;
    }
}
