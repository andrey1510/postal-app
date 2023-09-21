package com.postalservice.entitiesTests;

import com.postalservice.entities.PostalHistoryRecord;
import com.postalservice.entities.PostalItem;
import com.postalservice.entities.PostalOffice;
import com.postalservice.enums.PostalStatus;
import com.postalservice.testData.TestData;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PostalHistoryRecordTests extends TestData {

    @Test
    public void testCreation() {
        PostalStatus postalStatus = PostalStatus.IN_OFFICE;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PostalItem postalItem = createItem1();
        PostalOffice postalOffice = createOffice1();

        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(postalStatus, postalItem, postalOffice);
        postalHistoryRecord.setTimestamp(timestamp);

        assertEquals(timestamp, postalHistoryRecord.getTimestamp());
        assertEquals(postalStatus, postalHistoryRecord.getPostalStatus());
        assertEquals(postalItem, postalHistoryRecord.getPostalItem());
        assertEquals(postalOffice, postalHistoryRecord.getPostalOffice());
    }

    @Test
    public void testSetters() {
        PostalStatus postalStatus = PostalStatus.IN_OFFICE;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PostalItem postalItem = createItem1();
        PostalOffice postalOffice = createOffice1();

        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(postalStatus, postalItem, postalOffice);
        postalHistoryRecord.setTimestamp(timestamp);
        postalHistoryRecord.setPostalItem(postalItem);
        postalHistoryRecord.setPostalOffice(postalOffice);
        postalHistoryRecord.setPostalStatus(postalStatus);

        assertEquals(timestamp, postalHistoryRecord.getTimestamp());
        assertEquals(postalStatus, postalHistoryRecord.getPostalStatus());
        assertEquals(postalItem, postalHistoryRecord.getPostalItem());
        assertEquals(postalOffice, postalHistoryRecord.getPostalOffice());

    }

}
