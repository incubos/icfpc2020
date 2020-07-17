package icfpc2020;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.net.http.*;

class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var serverUrl = args[0];
        var playerKey = args[1];

        log.info("ServerUrl: {}; PlayerKey: {}", serverUrl, playerKey);
        var alienClient = new AlienMessageClient(HttpClient.newBuilder().build(), serverUrl);
        // First message should contain player key in body
        alienClient.sendMessage(new MessageImpl(playerKey));
        alienClient.sendMessage(new MessageImpl("1101000"));
        alienClient.sendMessage(new MessageImpl("11011000011101000"));
    }
}
