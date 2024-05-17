package com.nhnacademy.practice.mqtt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqttSubscriber {

    private final MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void receiveMessage(String payload) {

        System.out.println("Received Message: " + payload);
    }
}
