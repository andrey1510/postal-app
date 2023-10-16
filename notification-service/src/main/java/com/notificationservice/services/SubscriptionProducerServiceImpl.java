package com.notificationservice.services;

import com.notificationservice.dto.PostalSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionProducerServiceImpl implements SubscriptionProducerService {

    @Autowired
    private KafkaTemplate<String, PostalSubscription> kafkaTemplate;

    @Override
    public void sendMessage(String topic, PostalSubscription postalSubscription) {
        kafkaTemplate.send(topic, postalSubscription);
    }

}
