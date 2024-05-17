package com.nhnacademy.practice;

import com.nhnacademy.practice.mqtt.service.MqttPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class PracticeApplication implements CommandLineRunner {

	private final MqttPublisher publisher;

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		publisher.publishMessage("안뇽하세요");
	}
}
