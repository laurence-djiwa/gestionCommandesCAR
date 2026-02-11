package com.example.demo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommandeProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic = "commandes"; // Nom du topic Kafka

    @Autowired
    public CommandeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void envoyerCommande(String message) {
        kafkaTemplate.send(topic, message);
    }
}
