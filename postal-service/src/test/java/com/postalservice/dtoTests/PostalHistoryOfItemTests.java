package com.postalservice.dtoTests;

import com.postalservice.dto.PostalHistoryOfItem;
import com.postalservice.entities.PostalOffice;
import com.postalservice.enums.PostalStatus;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PostalHistoryOfItemTests {

    @Test
    public void testCreation() {
        PostalHistoryOfItem postalHistory = PostalHistoryOfItem.builder()
                .historyRecordId(UUID.randomUUID())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .postalStatus(PostalStatus.IN_OFFICE)
                .postalOffice(new PostalOffice(
                        "125009",
                        "Почтовое отделение № 125009",
                        "г. Москва, ул. Тверская, дом 9, стр. 5"
                ))
                .build();

        assertNotNull(postalHistory);
        assertNotNull(postalHistory.getHistoryRecordId());
        assertNotNull(postalHistory.getTimestamp());
        assertNotNull(postalHistory.getPostalStatus());
        assertNotNull(postalHistory.getPostalOffice());
    }

    @Test
    public void testGetUUID() {
        UUID uuid = UUID.randomUUID();
        PostalHistoryOfItem postalHistory = PostalHistoryOfItem.builder()
                .historyRecordId(uuid)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .postalStatus(PostalStatus.IN_OFFICE)
                .postalOffice(new PostalOffice(
                        "125009",
                        "Почтовое отделение № 125009",
                        "г. Москва, ул. Тверская, дом 9, стр. 5"
                ))
                .build();

        assertEquals(uuid, postalHistory.getHistoryRecordId());
    }

    @Test
    public void testGetTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PostalHistoryOfItem postalHistory = PostalHistoryOfItem.builder()
                .historyRecordId(UUID.randomUUID())
                .timestamp(timestamp)
                .postalStatus(PostalStatus.IN_OFFICE)
                .postalOffice(new PostalOffice(
                        "125009",
                        "Почтовое отделение № 125009",
                        "г. Москва, ул. Тверская, дом 9, стр. 5"
                ))
                .build();

        assertEquals(timestamp, postalHistory.getTimestamp());
    }

    @Test
    public void testGetPostalStatus() {
        PostalStatus postalStatus = PostalStatus.IN_OFFICE;
        PostalHistoryOfItem postalHistory = PostalHistoryOfItem.builder()
                .historyRecordId(UUID.randomUUID())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .postalStatus(postalStatus)
                .postalOffice(new PostalOffice(
                        "125009",
                        "Почтовое отделение № 125009",
                        "г. Москва, ул. Тверская, дом 9, стр. 5"
                ))
                .build();

        assertEquals(postalStatus, postalHistory.getPostalStatus());
    }

}