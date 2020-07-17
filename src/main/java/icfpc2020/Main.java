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
            var alienClient = new AlienMessageClient(httpClient, serverUrl, playerKey);
            alienClient.sendMessage(new MessageImpl("1101000"));
            alienClient.sendMessage(new MessageImpl("11011000011101000"));
            alienClient.sendMessage(new MessageImpl("11011000011101000"));
        } catch (Exception e) {
            log.error("Unexpected error", e);
            System.exit(1);
        }
    }

}
