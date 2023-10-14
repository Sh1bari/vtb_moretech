package com.example.moretech.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Configuration
@EnableAsync
public class OfficeScheduler {

    @Async
    @Scheduled(fixedRate = 5000)
    void sendInfo() throws IOException {
        post("http://localhost:8083/api/workload/2134", "OFFICE", generateRandomNumber(0, 10));
    }

    private static void post(String urlStr, String departmentType, Integer workload) throws IOException {
        System.out.println("запрос");
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-Type", "application/json");

        connection.setDoOutput(true);

        String jsonInputString = "{\"workload\": " + workload + ", \"departmentType\": \"" + departmentType + "\"}";

        byte[] inputBytes = jsonInputString.getBytes(StandardCharsets.UTF_8);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(inputBytes, 0, inputBytes.length);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        connection.disconnect();
    }

    private static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Минимальное значение должно быть меньше максимального значения.");
        }

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
