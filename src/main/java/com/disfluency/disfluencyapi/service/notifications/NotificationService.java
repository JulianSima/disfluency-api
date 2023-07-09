package com.disfluency.disfluencyapi.service.notifications;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.net.HttpURLConnection;
import java.io.OutputStreamWriter;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final String PROJECT_ID = "dislfuencyapp";
    private final String BASE_URL = "https://fcm.googleapis.com";
    private final String FCM_SEND_ENDPOINT = "/v1/projects/" + PROJECT_ID + "/messages:send";
    private final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private final String[] SCOPES = { MESSAGING_SCOPE };
    public final String MESSAGE_KEY = "message";

    private String getAccessToken() {
        try {
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new FileInputStream("service-account.json"))
                    .createScoped(Arrays.asList(SCOPES));
            credentials.refreshIfExpired();
            AccessToken token = credentials.refreshAccessToken();
            return token.getTokenValue();
        } catch (Exception e) {
            throw new RuntimeException("No puedo obtenerse el token de firebase: " + e.getMessage());
        }
    }

    private HttpURLConnection getConnection() {
        try {
            URL url = new URL(BASE_URL + FCM_SEND_ENDPOINT);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + getAccessToken());
            httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
            return httpURLConnection;
        } catch (Exception e) {
            throw new RuntimeException("No se pudo crear la llamada a firebase: " + e.getMessage());
        }
    }

    public void sendMessage(JsonObject fcmMessage) throws IOException {
        HttpURLConnection connection = getConnection();
        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        writer.write(fcmMessage.toString());
        writer.flush();
        writer.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = inputstreamToString(connection.getInputStream());
            System.out.println("Message sent to Firebase for delivery, response:");
            System.out.println(response);
        } else {
            System.out.println("Unable to send message to Firebase:");
            String response = inputstreamToString(connection.getErrorStream());
            System.out.println(response);
        }
    }

    public void sendCommonMessage(String title, String body, String fcmToken) throws IOException {
        JsonObject notificationMessage = buildNotificationMessage(title, body, fcmToken);
        System.out.println("FCM request body for message using common notification object:");
        prettyPrint(notificationMessage);
        sendMessage(notificationMessage);
    }

    private JsonObject buildNotificationMessage(String title, String body, String fcmToken) {
        JsonObject jNotification = new JsonObject();
        jNotification.addProperty("title", title);
        jNotification.addProperty("body", body);

        JsonObject jMessage = new JsonObject();
        jMessage.add("notification", jNotification);
        jMessage.addProperty("token", fcmToken);

        JsonObject jFcm = new JsonObject();
        jFcm.add(MESSAGE_KEY, jMessage);

        return jFcm;
    }

    private String inputstreamToString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

    private void prettyPrint(JsonObject jsonObject) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(jsonObject) + "\n");
    }
}
