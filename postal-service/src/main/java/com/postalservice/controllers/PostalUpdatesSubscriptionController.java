package com.postalservice.controllers;

import com.postalservice.entities.PostalSubscription;
import com.postalservice.services.PostalUpdatesSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api/postal_updates/")
@RequiredArgsConstructor
public class PostalUpdatesSubscriptionController {

    @Autowired
    private final PostalUpdatesSubscriptionService postalUpdatesSubscriptionService;




}
