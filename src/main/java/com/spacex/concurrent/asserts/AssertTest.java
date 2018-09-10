package com.spacex.concurrent.asserts;

public class AssertTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        mock();
    }

    public static void mock() {
        int num = 10;
        int flag = 10;
        boolean greatThanZero = greaterThan(num, flag);
        System.out.println(greatThanZero);
        assert greatThanZero : String.format(" %s less than %s", num, flag);
    }

    public static boolean greaterThan(int num, int flag) {
        return num > flag;
    }
}
