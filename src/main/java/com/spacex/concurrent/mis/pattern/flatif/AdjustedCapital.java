package com.spacex.concurrent.mis.pattern.flatif;

public class AdjustedCapital {

    private static final double ADJ_FACTOR = 0.5;
    private static double capital = 3.0;
    private static double initRate = 1.0;
    private static double duration = 1.0;
    private static double income = 10;


    public static double getAdjustedCapital() {
        double result = 0.0F;
        if (capital > 0.0) {
            if (initRate > 0 && duration > 0) {
                result = (income / duration) * ADJ_FACTOR;
            }

        }

        return result;
    }

    public static double getAdjustedCapitalGracefully() {

        if (capital <= 0.0) {
            return 0.0;
        }

        if (initRate <= 0 || duration <= 0) {
            return 0.0;
        }


        return (income / duration) * ADJ_FACTOR;
    }


}
