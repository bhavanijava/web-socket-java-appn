package com.web;

import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class MyWebSocketClient {

    private StompSession stompSession;

    public MyWebSocketClient() throws InterruptedException, ExecutionException {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        // Replace with your WebSocket server URL
        String serverUri = "ws://localhost:8080/gs-guide-websocket";

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompSession = stompClient.connect(serverUri, sessionHandler).get();
        
        // Create and send the initial message
        Output output = new Output();

        output.setKey("name");
        output.setValue("Shankar");
        
        sendInitialMessage(output);
    }

    public StompSession getStompSession() {
        return stompSession;
    }
    
    
    private void sendInitialMessage(Output output) {
        stompSession.send("/app/chat", output);
    }


}
