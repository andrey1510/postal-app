package com.postalservice.services;

import com.postalservice.dto.PostalUpdateDTO;
import com.postalservice.entities.PostalHistoryRecord;
import com.postalservice.entities.PostalSubscription;
import com.postalservice.repositories.PostalUpdatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostalUpdatesServiceImpl implements PostalUpdatesService {

    @Autowired
    private KafkaTemplate<String, PostalUpdateDTO> kafkaTemplate;

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

    @Override
    public void sendUpdates(String topic, PostalUpdateDTO postalUpdateDTO) {
        kafkaTemplate.send(topic, postalUpdateDTO);
    }

    @Override
    public PostalUpdateDTO gatherUpdates(PostalHistoryRecord postalHistoryRecord) {
        PostalUpdateDTO postalUpdateDTO = new PostalUpdateDTO();

        postalUpdateDTO.setPostalItemId(postalHistoryRecord.getPostalItem().getPostalItemId());
        postalUpdateDTO.setTimestamp(String.valueOf(postalHistoryRecord.getTimestamp()));
        postalUpdateDTO.setPostalStatus(String.valueOf(postalHistoryRecord.getPostalStatus()));

        return postalUpdateDTO;

    }

}
