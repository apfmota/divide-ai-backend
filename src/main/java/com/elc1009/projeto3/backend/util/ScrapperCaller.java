package com.elc1009.projeto3.backend.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.util.stream.Collectors;

public class ScrapperCaller {

    public static String call(String url) {
        try {
            File scriptFile = new File("scrapper/main.py");
            ProcessBuilder processBuilder = new ProcessBuilder("python", scriptFile.getPath(), url);
            
            System.out.println("Launching script with URL: " + url);
            System.out.println("Current working directory: " + System.getProperty("user.dir"));

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
            return "{ \"err\": \"Erro ao executar Scrapper\"} ";
        }
    }
}
