package com.notificationservice.services;


import com.notificationservice.dto.PostalSubscription;
import com.notificationservice.entities.PostalUpdate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface PostalUpdatesService {

    void sendMessage(String topic, PostalSubscription postalSubscription);

    PostalUpdate createPostalUpdate(PostalUpdate postalUpdate);

    PostalUpdate getLastPostalUpdate(UUID postalItemId);

    List<PostalUpdate> getAllPostalUpdatesByPostalItemId(UUID postalItemId);
}
