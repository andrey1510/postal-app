package com.notificationservice.services;


import com.notificationservice.dto.PostalSubscription;

public interface SubscriptionProducerService {

    void sendMessage(String topic, PostalSubscription postalSubscription);
}
