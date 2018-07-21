package com.spacex.concurrent;

import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class StringSubstitutorTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        String template = "${username} like your works at ${timeStamp}";
        Map<String, String> map = new HashMap<>();
        map.put("username", "Elon Musk");
        map.put("timeStamp", "2018-07-20 11:47:00");
        String result = StrSubstitutor.replace(template, map);
        System.out.println(result);
    }
}