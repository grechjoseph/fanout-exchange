package com.jg.fanoutconsumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface FanoutBinding {

    public final String EXCHANGE = "jg-fanout-exchange";

    @Input(EXCHANGE)
    SubscribableChannel jgFanoutExchange();

}
