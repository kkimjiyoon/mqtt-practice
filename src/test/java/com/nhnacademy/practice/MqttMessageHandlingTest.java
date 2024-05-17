package com.nhnacademy.practice;

import com.nhnacademy.practice.mqtt.service.MqttPublisher;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MqttMessageHandlingTest {

    @InjectMocks
    private MqttPublisher mqttPublisher;

    @Mock
    private MqttClient mqttClient;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPublishMessage() throws Exception {

        MqttMessage message = new MqttMessage();
        message.setPayload("Test Message :" .getBytes());

        mqttPublisher.startTemperatureIncrease();

        verify(mqttClient, times(1)).publish(eq("sensor/temperature"), any(MqttMessage.class));
    }
}
