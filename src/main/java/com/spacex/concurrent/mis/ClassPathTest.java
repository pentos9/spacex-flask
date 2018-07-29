package com.spacex.concurrent.mis;

import com.google.common.reflect.ClassPath;

import java.io.IOException;

public class ClassPathTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        try {
            final String packageName = "com.spacex";
            ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());

            for (ClassPath.ClassInfo classInfo : classPath.getTopLevelClassesRecursive(packageName)) {
                System.out.println(String.format("[Package] %s", classInfo.getName()));
            }

            for (ClassPath.ClassInfo classInfo : classPath.getAllClasses()) {
                System.out.println(String.format("[Package-All-Class] %s", classInfo.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
