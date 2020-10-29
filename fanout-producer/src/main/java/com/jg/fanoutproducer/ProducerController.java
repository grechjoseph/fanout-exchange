package com.jg.fanoutproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@EnableBinding(FanoutBinding.class)
public class ProducerController {

    private final FanoutBinding fanoutBinding;

    @PostMapping
    public void postMessage(@RequestParam(name = "message") final String message) {
        fanoutBinding.jgFanoutExchange().send(MessageBuilder.withPayload(message).build());
        log.info("Published message to exchange.");
    }

}
