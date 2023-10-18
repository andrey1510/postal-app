package com.postalservice.controllers;

import com.postalservice.dto.PostalSubscriptionDTO;
import com.postalservice.entities.PostalSubscription;
import com.postalservice.services.PostalUpdatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class PostalUpdatesController {

    private static final String TOPIC_NAME = "${spring.kafka.subscription-topic.name}";
    private static final String GROUP_ID = "${spring.kafka.consumer.group-id}";

    private final PostalUpdatesService postalUpdatesService;

    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID, properties = {"spring.json.value.default.type=com.postalservice.dto.PostalSubscriptionDTO"})
    public void createOrUpdateSubscription(PostalSubscriptionDTO postalSubscriptionDTO) {

        UUID postalItemId = postalSubscriptionDTO.getPostalItemId();
        boolean subscriptionStatus = postalSubscriptionDTO.isSubscriptionStatus();

        if(postalUpdatesService.findById(postalItemId).isEmpty()) {
            postalUpdatesService.createPostalSubscription(new PostalSubscription(postalItemId, subscriptionStatus));
        } else {
            postalUpdatesService.updateSubscriptionStatusById(subscriptionStatus, postalItemId);
        }

    }

}