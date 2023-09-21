package com.postalservice.services;

import com.postalservice.entities.PostalItem;
import com.postalservice.repositories.PostalItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostalItemServiceImpl implements PostalItemService {

    @Autowired
    private PostalItemRepository postalItemRepository;

    @Override
    @Transactional
    public PostalItem createPostalItem(PostalItem postalItem) {
        return postalItemRepository.save(postalItem);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostalItem> findById(UUID postalItemId) {
        return postalItemRepository.findById(postalItemId);
    }

}
