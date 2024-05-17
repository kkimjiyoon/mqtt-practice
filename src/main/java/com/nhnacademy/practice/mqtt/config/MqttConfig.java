package com.nhnacademy.practice.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

@Configuration
public class MqttConfig {
    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String MQTT_PUB_CLIENT_ID = MqttAsyncClient.generateClientId();
    private static final String MQTT_SUB_CLIENT_ID = MqttAsyncClient.generateClientId();

    private static final String TOPIC_FILTER = "#";

    private MqttClient mqttClient;

    @Bean
    public MqttConnectOptions mqttConnectOptions() {

        MqttConnectOptions option = new MqttConnectOptions();

        option.setServerURIs(new String[]{BROKER_URL});
        return option;
    }

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttConnectOptions());

        return factory;
    }

    @Bean
    public MqttClient mqttClient() throws MqttException {
        if (mqttClient == null || !mqttClient.isConnected()) {
            mqttClient = new MqttClient(BROKER_URL, MQTT_SUB_CLIENT_ID);
            mqttClient.connect(mqttConnectOptions());
        }
        return mqttClient;
//        return new MqttClient(BROKER_URL, MQTT_SUB_CLIENT_ID);
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter(MqttPahoClientFactory mqttPahoClientFactory) {

        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(MQTT_PUB_CLIENT_ID, mqttPahoClientFactory, TOPIC_FILTER);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannelName("mqttInputChannel");

        return adapter;
    }

}
