package com.elc1009.projeto3.backend.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ScrapperCaller {
    
    public static void call(String url) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/main/python/scrapper.py", url);
            Process process = processBuilder.start();
            BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
            output.lines().forEach(System.out::println);    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
