package com.nhnacademy.practice.mqtt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MqttPublisher {
    private final MqttClient mqttClient;
    private int temperature = 0;
    public void publishMessage(String message) throws MqttException {

        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(message.getBytes());

        mqttClient.publish("test", mqttMessage);
    }

    public void startTemperatureIncrease() {

        try {
            temperature += 1;

            MqttMessage message = new MqttMessage();
            message.setPayload(String.valueOf(temperature).getBytes());

            System.out.println(temperature);

            mqttClient.publish("sensor/temperature", message);

            if (temperature >= 100) {
                log.warn("온도 이상");
            }
        } catch(MqttException e) {
            e.printStackTrace();
        }
    }

}
