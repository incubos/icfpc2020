package icfpc2020;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author incubos
 */
public class API {
    private static final Logger log = LoggerFactory.getLogger(API.class);

    private static final String ENDPOINT = "https://icfpc2020-api.testkontur.ru";
    private static final String API_KEY = "3132acdb670045d3b93482f7e0b65359";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    @NotNull
    public static String send(@NotNull final String body) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT + "/aliens/send?apiKey=" + API_KEY))
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        log.info("Sending request: {}", request);

        final HttpResponse<String> response;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            log.error("Error when sending request: {}", request, e);
            throw new RuntimeException("Error when sending request", e);
        }

        if (response.statusCode() != HttpURLConnection.HTTP_OK) {
            log.error("Bad server response: {}", response);
            throw new RuntimeException("Bad response");
        }

        return response.body();
    }
}
