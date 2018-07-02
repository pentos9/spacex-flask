package com.spacex.concurrent.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MisFutureTest {

    public static Logger logger = LoggerFactory.getLogger(MisFutureTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        logger.info(null);
    }
}
