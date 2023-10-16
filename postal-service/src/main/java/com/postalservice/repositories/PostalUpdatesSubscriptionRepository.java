package com.postalservice.repositories;

import com.postalservice.entities.PostalSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostalUpdatesSubscriptionRepository  extends JpaRepository<PostalSubscription, UUID> {
}
