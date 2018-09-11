package com.spacex.concurrent.mis.pattern;

public class TestUpdate {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        test(new I18NUpdate());
        test(new NonI18NUpdate());
    }

    public static void test(Update update) {
        if (update == null) {
            return;
        }
        update.execute();
        update.render();

        // assert statement
    }
}
