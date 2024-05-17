package com.nhnacademy.practice.mqtt.controller;

import com.nhnacademy.practice.mqtt.service.MqttPublisher;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MqttController {

    private final MqttPublisher mqttPublisher;

    @PostMapping("/test")
    ResponseEntity<String> mqttTest() throws MqttException {

        mqttPublisher.startTemperatureIncrease();

        return ResponseEntity.ok().body("온도 증가");
    }

}
