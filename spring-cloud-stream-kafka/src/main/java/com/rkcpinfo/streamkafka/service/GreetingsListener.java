package com.rkcpinfo.streamkafka.service;

import com.rkcpinfo.streamkafka.model.Greetings;
import com.rkcpinfo.streamkafka.stream.GreetingsStreams;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreetingsListener {
    @StreamListener(GreetingsStreams.INPUT)
    public void handleGreetings(@Payload Greetings greetings) {
        System.out.println("Received greetings: {}"+ greetings);
    }
}
