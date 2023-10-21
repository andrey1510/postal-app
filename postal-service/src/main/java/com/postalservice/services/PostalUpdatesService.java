package com.postalservice.services;

import com.postalservice.dto.PostalUpdateDTO;
import com.postalservice.entities.PostalHistoryRecord;
import com.postalservice.entities.PostalSubscription;

import java.util.Optional;
import java.util.UUID;

public interface PostalUpdatesService {

    Optional<PostalSubscription> findById(UUID id);

    PostalSubscription createPostalSubscription(PostalSubscription postalSubscription);

    void updateSubscriptionStatusById(Boolean subscriptionStatus, UUID postalItemId);

    void sendUpdates(String topic, PostalUpdateDTO postalUpdateDTO);

    PostalUpdateDTO gatherUpdates(PostalHistoryRecord postalHistoryRecord);
}
