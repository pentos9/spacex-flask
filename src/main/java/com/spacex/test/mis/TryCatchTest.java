package com.spacex.test.mis;

public class TryCatchTest {
    public static void main(String[] args) {
        System.out.println(run());
    }

    public static String run() {
        long current = System.currentTimeMillis();
        try {
            String init = "";
            System.out.println("do something:" + current);
//            if (true) {
//                throw new Exception("error!");
//            }

            return "return statement";
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            System.out.println("Finally block!");
            System.out.println("Finally block!");
            System.out.println("Finally block!");
            return "[finally] block return";
        }


    }
}
