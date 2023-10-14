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

@Configuration
@EnableAsync
public class OfficeScheduler {

    @Async
    @Scheduled(fixedRate = 5000)
    void sendIfo() throws NoSuchAlgorithmException, IOException {
        System.out.println("запрос");
        URL url = new URL("http://localhost:8083/api/workload/2134");

        // Открываем соединение
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Устанавливаем метод запроса на POST
        connection.setRequestMethod("POST");

        // Устанавливаем заголовок Content-Type как application/json
        connection.setRequestProperty("Content-Type", "application/json");

        // Включаем вывод данных
        connection.setDoOutput(true);

        // Создаем JSON-строку для отправки
        String jsonInputString = "{\"workload\": 1, \"departmentType\": \"OFFICE\"}";

        // Преобразуем JSON-строку в байты
        byte[] inputBytes = jsonInputString.getBytes(StandardCharsets.UTF_8);

        // Получаем выходной поток для записи данных
        try (OutputStream os = connection.getOutputStream()) {
            os.write(inputBytes, 0, inputBytes.length);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        connection.disconnect();

    }
}
