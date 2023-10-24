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

    //PostalUpdate getPostalUpdateByPostalItemIdOrderByTimestampDesc(@Param("postal_item_id") UUID postalItemId);

    //ToDo не работает, т.к. String timestamp не сортируется по времени
//    @Query("select id, postalItemId, postalStatus, timestamp " +
//            "from PostalUpdate " +
//            "where postalItemId = :postalItemId " +
//            "order by timestamp desc " +
//            "limit 1")

//        @Query(value = "SELECT id, postalItemId, postalStatus, timestamp " +
//            "FROM postal_update " +
//            "WHERE postal_item_id = :postalItemId " +
//            "OFFSET (COUNT(SELECT(*) FROM postal_update WHERE postal_item_id = :postalItemId) - 1) ",
//                nativeQuery = true)
//    PostalUpdate getLastUpdate(@Param("postal_item_id") UUID postalItemId);

    List<PostalUpdate> getPostalUpdatesByPostalItemId(@Param("postal_item_id") UUID postalItemId);

}
