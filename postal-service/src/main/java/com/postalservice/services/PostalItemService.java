package com.postalservice.services;

import com.postalservice.entities.PostalItem;

import java.util.Optional;
import java.util.UUID;

public interface PostalItemService {

    PostalItem createPostalItem(PostalItem postalItem);

    Optional<PostalItem> findById(UUID postalItemId);
}
