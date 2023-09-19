package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@ComponentScan(basePackages = "com.web")
public class WebSocketsAppnApplication {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SpringApplication.run(WebSocketsAppnApplication.class, args);

        MyWebSocketClient webSocketClient = new MyWebSocketClient();
        webSocketClient.getStompSession();

        while (true) {
            Thread.sleep(1000); 
        }
    }
}
