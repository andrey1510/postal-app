package com.postalservice.repositories;

import com.postalservice.entities.PostalSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostalUpdatesRepository extends JpaRepository<PostalSubscription, UUID> {


    @Transactional
    @Modifying
    @Query("update PostalSubscription s set s.subscriptionStatus = :subscriptionStatus where s.postalItemId = :postalItemId")
    void updateSubscriptionStatusById(Boolean subscriptionStatus, UUID postalItemId);

    @Query("select " +
            "r.postalItem.postalItemId, " +
            "r.timestamp, " +
            "r.postalStatus " +
            "from PostalHistoryRecord r " +
            "left join PostalItem i on r.postalItem.postalItemId = i.postalItemId " +
            "where r.postalItem.postalItemId = :postal_item_id  " +
            "order by " +
            "r.timestamp desc " +
            "limit 1")
    List<Object[]> getPostalHistory(@Param("postal_item_id") UUID postalItemId);
}
