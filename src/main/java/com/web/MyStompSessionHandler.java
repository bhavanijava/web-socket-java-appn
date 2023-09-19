package com.web;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;


@Component
@Slf4j
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
    	System.out.println("Server Conneted ...");
        // Subscribe to the "/topic/messages" WebSocket topic
        session.subscribe("/topic/messages", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Output.class; // Specify the payload type (Message class)
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                Output msg = (Output) payload;
                // Handle incoming messages here
                log.info("Received : " + msg.getKey()+ " : " + msg.getValue());
            }
        });

 
//        String messageText = "Hello, server!";
//        Message message = new Message();
//        message.setText(messageText);
//        session.send("/app/chat", message);
    }
}
