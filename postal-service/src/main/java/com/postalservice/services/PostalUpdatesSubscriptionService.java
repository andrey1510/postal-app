package com.postalservice.services;

import com.postalservice.entities.PostalSubscription;
import java.util.Optional;
import java.util.UUID;

public interface PostalUpdatesSubscriptionService {

    Optional<PostalSubscription> findById(UUID id);

    PostalSubscription createPostalUpdatesSubscription(PostalSubscription postalUpdatesSubscription);
}
