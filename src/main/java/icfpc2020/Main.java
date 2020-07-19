package icfpc2020;

import icfpc2020.api.PrivateAPIImpl;
import icfpc2020.operators.Modulate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.net.*;
import java.net.http.*;
import java.text.MessageFormat;
import java.util.List;

class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            var serverUrl = args[0];
            var playerKeyString = args[1];
            boolean local = false;
            if (args.length > 2) {
                local = true;
            }

            log.info("Server URL: {}, player key: {}", serverUrl, playerKeyString);
            HttpClient httpClient = HttpClient.newBuilder().build();
            PrivateAPIImpl privateAPI = new PrivateAPIImpl(httpClient, local ? serverUrl : serverUrl + "/aliens/send");
            String send = privateAPI.send(Commands.create());
            log.info("Create response={}", send);
            log.info("dem={}", DemodulateList.dem(new MessageImpl(send)));
            String send1 = privateAPI.send(Commands.join(playerKeyString));
            log.info("Join command response={}}", send1);
            log.info("dem={}", DemodulateList.dem(new MessageImpl(send1)));
            String send2 = privateAPI.send(Commands.start(playerKeyString, "12", "23", "34", "45"));
            log.info("Start command response={}", send2);
            log.info("dem={}", DemodulateList.dem(new MessageImpl(send2)));


        } catch (Exception e) {
            log.error("Unexpected error", e);
            System.exit(1);
        }
    }

}

/**
 * message sender examples
 * <p>
 * //            alienClient.sendMessage(new MessageImpl())
 * //            var request = HttpRequest.newBuilder()
 * //                                     .POST(HttpRequest.BodyPublishers.ofString(playerKey))
 * //                                     .version(HttpClient.Version.HTTP_1_1)
 * //                                     .uri(URI.create(serverUrl))
 * //                                     .build();
 * //
 * //            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
 * //            var status = response.statusCode();
 * //            var responseBody = response.body();
 * //            if (status != HttpURLConnection.HTTP_OK) {
 * //                log.error("Unexpected server response:");
 * //                log.error("HTTP code: {}", status);
 * //                log.error("Response body: {}", responseBody);
 * //                throw new RuntimeException("Got unexpected server response code");
 * //            }
 * //            log.info("Server response: {}", responseBody);
 * // (0,nil)
 * //            alienClient.sendMessage(new MessageImpl("1101000"));
 * // (1,0,nil)
 * //            alienClient.sendMessage(new MessageImpl("11011000011101000"));
 * // (1,0,nil)
 * //            alienClient.sendMessage(new MessageImpl("11011000011101000"));
 * // random to confirm echo behaviour
 * //            alienClient.sendMessage(new MessageImpl("11111111111111111111111111111111"));
 */
