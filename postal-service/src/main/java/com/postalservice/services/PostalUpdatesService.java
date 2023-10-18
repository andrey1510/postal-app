package com.postalservice.services;

import com.postalservice.entities.PostalSubscription;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface PostalUpdatesService {

    Optional<PostalSubscription> findById(UUID id);

    PostalSubscription createPostalSubscription(PostalSubscription postalSubscription);

    void updateSubscriptionStatusById(Boolean subscriptionStatus, UUID postalItemId);
}
