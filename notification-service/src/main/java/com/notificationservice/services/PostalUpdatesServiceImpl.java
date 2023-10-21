package com.notificationservice.services;

import com.notificationservice.dto.PostalSubscription;
import com.notificationservice.entities.PostalUpdate;
import com.notificationservice.repositories.PostalUpdatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PostalUpdatesServiceImpl implements PostalUpdatesService {

    @Autowired
    private KafkaTemplate<String, PostalSubscription> kafkaTemplate;

    @Autowired
    private PostalUpdatesRepository postalUpdatesRepository;

    @Override
    public void sendMessage(String topic, PostalSubscription postalSubscription) {
        kafkaTemplate.send(topic, postalSubscription);
    }

    @Override
    @Transactional
    public PostalUpdate createPostalUpdate(PostalUpdate postalUpdate) {
        return postalUpdatesRepository.save(postalUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public PostalUpdate getLastPostalUpdateByPostalItemId(UUID postalItemId) {
        return postalUpdatesRepository.getPostalUpdateByPostalItemIdOrderByTimestampDesc(postalItemId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostalUpdate> getAllPostalUpdatesByPostalItemId(UUID postalItemId) {
        return postalUpdatesRepository.getPostalUpdatesByPostalItemId(postalItemId);
    }

}
