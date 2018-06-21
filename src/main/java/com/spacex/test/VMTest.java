package com.spacex.test;

import com.spacex.bean.VirtualMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VMTest {
    private static Logger logger = LoggerFactory.getLogger(VMTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        VirtualMachine virtualMachine = new VirtualMachine(985341L, "JVM", "HotSpot", "JDK8");
        VirtualMachine dartVM = new VirtualMachine(985342L, "Dart", "D8", "Dart8");
        VirtualMachine v8 = new VirtualMachine(985343L, "V8", "V8", "V8Engine");
        VirtualMachine dalvik = new VirtualMachine(985344L, "Dalvik", "Dalvik", "Dalvik");
        logger.info(String.format("virtualMachine:%s", virtualMachine));
        logger.info(String.format("dartVM:%s", dartVM));
        logger.info(String.format("v8:%s", v8));
        logger.info(String.format("dalvik:%s", dalvik));
    }
}
