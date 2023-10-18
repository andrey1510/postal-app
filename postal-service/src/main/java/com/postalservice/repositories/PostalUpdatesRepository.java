package com.postalservice.repositories;

import com.postalservice.entities.PostalSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface PostalUpdatesRepository extends JpaRepository<PostalSubscription, UUID> {


    @Transactional
    @Modifying
    @Query("update PostalSubscription s set s.subscriptionStatus = :subscriptionStatus where s.postalItemId = :postalItemId")
    void updateSubscriptionStatusById(Boolean subscriptionStatus, UUID postalItemId);


}
