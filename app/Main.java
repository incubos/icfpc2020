import java.net.*;
import java.net.http.*;

class Main {
    public static void main(String[] args) throws Exception {
        try {
            var serverUrl = args[0];
            var playerKey = args[1];

            System.out.println("ServerUrl: " + serverUrl + "; PlayerKey: " + playerKey);

            var request = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl))
                    .version(HttpClient.Version.HTTP_1_1)
                    .POST(HttpRequest.BodyPublishers.ofString(playerKey))
                    .build();

            var response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            var status = response.statusCode();

            if (status != HttpURLConnection.HTTP_OK) {
                System.out.println("Unexpected server response:");
                System.out.println("HTTP code: " + status);
                System.out.println("Response body: " + response.body());
                System.exit(2);
            }

            System.out.println("Server response: " + response.body());
        } catch (Exception e) {
            System.out.println("Unexpected server response:");
            e.printStackTrace(System.out);
            System.exit(1);
        }
    }
}
