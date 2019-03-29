package com.rkcpinfo.streamkafka.config;

import com.rkcpinfo.streamkafka.stream.GreetingsStreams;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {
}
