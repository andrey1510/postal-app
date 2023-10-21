package com.postalservice.repositories;

import com.postalservice.entities.PostalHistoryRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostalHistoryRecordRepository extends JpaRepository<PostalHistoryRecord, UUID> {

    @Query("select " +
                "r.historyRecordId, " +
                "r.timestamp, " +
                "r.postalStatus, " +
                "o.officeTitle, " +
                "o.officeIndex, " +
                "o.officeAddress " +
            "from " +
                "PostalHistoryRecord r " +
                "left join PostalItem i on r.postalItem.postalItemId = i.postalItemId " +
                "left join PostalOffice o on r.postalOffice.officeIndex = o.officeIndex " +
            "where r.postalItem.postalItemId = :postal_item_id  " +
            "order by " +
                "r.timestamp desc ")
    List<Object[]> getPostalHistory(@Param("postal_item_id") UUID postalItemId);


    PostalHistoryRecord getPostalHistoryRecordByPostalItem_PostalItemIdOrderByTimestampDesc(
            @Param("postal_item_id") UUID postalItemId);

}
