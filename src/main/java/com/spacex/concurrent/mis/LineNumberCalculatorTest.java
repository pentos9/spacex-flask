package com.spacex.concurrent.mis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.List;

public class LineNumberCalculatorTest {

    private static Logger logger = LoggerFactory.getLogger(LineNumberCalculatorTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        final String path = "/Users/lucas/projects/spacex-flask";
        final String suffix = ".java";
        final File file = new File(path);
        long totalLine = calculateLineNumber(file, suffix);
        System.out.println("Total:" + totalLine);

        long total = 0;
        final List<String> paths = Arrays.asList("/Users/lucas/projects/spacex-flask", "/Users/lucas/projects/spacex-rider", "/Users/lucas/projects/spacex-shane", "/Users/lucas/projects/treasure", "/Users/lucas/projects/flask-rocket");
        for (String item : paths) {
            long count = calculateLineNumber(new File(item), suffix);
            total = total + count;
        }

        System.out.println("Total:" + total);

    }

    public static long calculateLineNumber(File file, String suffix) {
        long lineNumber = 0;

        if (file == null || !file.exists()) {
            return lineNumber;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            for (File childFile : childFiles) {
                long lineNumberOfChildFile = calculateLineNumber(childFile, suffix);//recursive
                lineNumber = lineNumberOfChildFile + lineNumber;
            }

        } else {
            try {

                String fileName = file.getName();
                if (!fileName.toLowerCase().endsWith(suffix)) {
                    return 0;
                }

                FileReader fileReader = new FileReader(file);
                LineNumberReader lineNumberReader = new LineNumberReader(fileReader);

                while (lineNumberReader.readLine() != null) {
                    lineNumber++;
                }

                logger.info(String.format("%s ,total number of lines:%s", file.getPath(), lineNumber));
                lineNumberReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lineNumber;
    }
}
