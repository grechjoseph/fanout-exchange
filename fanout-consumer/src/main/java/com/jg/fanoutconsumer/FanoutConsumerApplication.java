package com.jg.fanoutconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@SpringBootApplication
@EnableBinding(FanoutBinding.class)
public class FanoutConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanoutConsumerApplication.class, args);
	}

	@StreamListener(FanoutBinding.EXCHANGE)
	public void listen(final String message) {
		log.info("Received message: {}", message);
	}

}
