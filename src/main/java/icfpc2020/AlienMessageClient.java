package icfpc2020;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AlienMessageClient {
    private static final Logger log = LoggerFactory.getLogger(AlienMessageClient.class);
    private final HttpClient httpClient;
    private final String serverUrl;
    private final String playerKey;

    public AlienMessageClient(final HttpClient httpClient, final String serverUrl, final String playerKey) {
        this.httpClient = httpClient;
        this.serverUrl = serverUrl;
        this.playerKey = playerKey;
    }

    public Message sendMessage(final Message message) {
        String messageString = message.toString();
        var request = HttpRequest.newBuilder()
                                 .POST(HttpRequest.BodyPublishers.ofString(messageString))
                                 .version(HttpClient.Version.HTTP_1_1)
                                 .uri(URI.create(serverUrl + "?apiKey=" + playerKey))
                                 .build();
        try {
            log.info("Sending message {}", messageString);
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
            return new MessageImpl(responseBody);
        } catch (Exception e) {
            log.error("Unexpected server response", e);
            throw new RuntimeException(e);
        }
    }
}
