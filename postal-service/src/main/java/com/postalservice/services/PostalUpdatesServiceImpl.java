package com.postalservice.services;

import com.postalservice.entities.PostalSubscription;
import com.postalservice.repositories.PostalUpdatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostalUpdatesServiceImpl implements PostalUpdatesService {

    @Autowired
    private PostalUpdatesRepository postalUpdatesRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<PostalSubscription> findById(UUID id) {
        return postalUpdatesRepository.findById(id);
    }

    @Override
    @Transactional
    public PostalSubscription createPostalSubscription(PostalSubscription postalSubscription) {
        return postalUpdatesRepository.save(postalSubscription);
    }

    @Override
    @Transactional
    public void updateSubscriptionStatusById(Boolean subscriptionStatus, UUID postalItemId) {
        postalUpdatesRepository.updateSubscriptionStatusById(subscriptionStatus, postalItemId);
    }

}
