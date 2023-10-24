package com.notificationservice.repositories;

import com.notificationservice.entities.PostalUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostalUpdatesRepository extends JpaRepository<PostalUpdate, UUID> {

    @Query("SELECT p FROM PostalUpdate p WHERE p.postalItemId = :postal_item_id ORDER BY p.timestamp DESC LIMIT 1")
    PostalUpdate getLastUpdate(@Param("postal_item_id") UUID postalItemId);

    List<PostalUpdate> getPostalUpdatesByPostalItemId(@Param("postal_item_id") UUID postalItemId);

}
