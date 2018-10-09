package com.spacex.concurrent.mis.pattern.nonif;

public class PreRefactorPay {
    private static boolean isDead = false;
    private static boolean isSeparated = false;
    private static boolean isRetired = false;

    double getPayAmount() {
        double result = 0F;
        if (isDead) {
            result = PayStrategy.deadAmount();
        } else {

            if (isSeparated) {
                result = PayStrategy.separatedAmount();
            } else {

                if (isRetired) {
                    result = PayStrategy.retiredAmount();
                } else {
                    result = PayStrategy.normalPayAmount();
                }
            }
        }

        return result;
    }


    double getPayAmountGracefully() {
        if (isDead) {
            return PayStrategy.deadAmount();
        }

        if (isSeparated) {
            return PayStrategy.separatedAmount();
        }

        if (isRetired) {
            return PayStrategy.retiredAmount();
        }

        return PayStrategy.normalPayAmount();
    }
}
