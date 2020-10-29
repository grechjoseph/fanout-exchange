package com.jg.fanoutproducer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FanoutBinding {

    String EXCHANGE = "jg-fanout-exchange";

    @Output(EXCHANGE)
    MessageChannel jgFanoutExchange();

}
