package com.notificationservice.controllers;

import com.notificationservice.dto.PostalSubscription;
import com.notificationservice.services.SubscriptionProducerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api/subscription/")
@RequiredArgsConstructor
public class PostalSubscriptionController {

    @Value("${spring.kafka.subscription-topic.name}")
    private String topic;

    private final SubscriptionProducerService subscriptionProducerService;

    @PostMapping("subscribe")
    @Operation(description = "Подписаться на получение уведомлений об изменении статуса почтового отправления")
    public ResponseEntity<UUID> subscribeToUpdates(@RequestBody UUID id) {

        subscriptionProducerService.sendMessage(topic, new PostalSubscription(id, true));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("unsubscribe")
    @Operation(description = "Отписаться от получения уведомлений об изменении статуса почтового отправления")
    public ResponseEntity<UUID> unsubscribeToUpdates(@RequestBody UUID id) {

        subscriptionProducerService.sendMessage(topic, new PostalSubscription(id, false));

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
