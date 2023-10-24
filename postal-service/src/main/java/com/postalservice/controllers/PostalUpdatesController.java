package com.postalservice.controllers;

import com.postalservice.dto.PostalSubscriptionDTO;
import com.postalservice.entities.PostalSubscription;
import com.postalservice.services.PostalUpdatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class PostalUpdatesController {

    private final PostalUpdatesService postalUpdatesService;

    @KafkaListener(topics = "${spring.kafka.subscription-topic.name}",
                   groupId = "${spring.kafka.consumer.group-id}",
                   properties = {"spring.json.value.default.type=com.postalservice.dto.PostalSubscriptionDTO"})
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
