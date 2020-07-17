package icfpc2020;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.net.http.*;

class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            var serverUrl = args[0];
            var playerKey = args[1];

            log.info("ServerUrl: {}; PlayerKey: {}", serverUrl, playerKey);
            HttpClient httpClient = HttpClient.newBuilder().build();
            var alienClient = new AlienMessageClient(httpClient, serverUrl);
            var request = HttpRequest.newBuilder()
                                     .POST(HttpRequest.BodyPublishers.ofString(playerKey))
                                     .version(HttpClient.Version.HTTP_1_1)
                                     .uri(URI.create(serverUrl))
                                     .build();

            // First message should contain player key in body
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var status = response.statusCode();
            var responseBody = response.body();
            if (status != HttpURLConnection.HTTP_OK) {
                log.error("Unexpected server response:");
                log.error("HTTP code: {}", status);
                log.error("Response body: {}", responseBody);
                throw new RuntimeException("Got unexpected server response code");
            }
            log.info("Server response: {}", responseBody);
            alienClient.sendMessage(new MessageImpl("1101000"));
            alienClient.sendMessage(new MessageImpl("11011000011101000"));
            alienClient.sendMessage(new MessageImpl("11011000011101000"));
        } catch (Exception e) {
            log.error("Unexpected error", e);
            System.exit(1);
        }
    }

}
