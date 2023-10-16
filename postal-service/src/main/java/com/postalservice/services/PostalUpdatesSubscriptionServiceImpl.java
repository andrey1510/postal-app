package com.postalservice.services;

import com.postalservice.entities.PostalSubscription;
import com.postalservice.repositories.PostalUpdatesSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostalUpdatesSubscriptionServiceImpl implements PostalUpdatesSubscriptionService {

    @Autowired
    private PostalUpdatesSubscriptionRepository postalUpdatesSubscriptionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<PostalSubscription> findById(UUID id) {
        return postalUpdatesSubscriptionRepository.findById(id);
    }

    @Override
    @Transactional
    public PostalSubscription createPostalUpdatesSubscription(PostalSubscription postalUpdatesSubscription) {
        return postalUpdatesSubscriptionRepository.save(postalUpdatesSubscription);
    }

}
