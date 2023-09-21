package com.postalservice.utilitiesTests;

import com.postalservice.dto.PostalHistoryOfItem;
import com.postalservice.enums.PostalStatus;
import com.postalservice.utilities.HistoryConverter;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HistoryConverterTests {

    @Test
    public void testMapToPostalHistoryOfItem() {

        List<Object[]> result = new ArrayList<>();
        Object[] obj1 = {
                UUID.randomUUID(),
                new Timestamp(System.currentTimeMillis()),
                PostalStatus.IN_OFFICE,
                "Office 1",
                "123456",
                "Address 1"
        };
        Object[] obj2 = {
                UUID.randomUUID(),
                new Timestamp(System.currentTimeMillis()),
                PostalStatus.OUT_OF_OFFICE,
                "Office 2",
                "654321",
                "Address 2"};
        result.add(obj1);
        result.add(obj2);

        List<PostalHistoryOfItem> postalHistoryOfItems = HistoryConverter.mapToPostalHistoryOfItemDTO(result);

        assertEquals(2, postalHistoryOfItems.size());
        assertEquals(obj1[0], postalHistoryOfItems.get(0).getHistoryRecordId());
        assertEquals(obj1[1], postalHistoryOfItems.get(0).getTimestamp());
        assertEquals(obj1[2], postalHistoryOfItems.get(0).getPostalStatus());
        assertEquals(obj1[3], postalHistoryOfItems.get(0).getPostalOffice().getOfficeTitle());
        assertEquals(obj1[4], postalHistoryOfItems.get(0).getPostalOffice().getOfficeIndex());
        assertEquals(obj1[5], postalHistoryOfItems.get(0).getPostalOffice().getOfficeAddress());
        assertEquals(obj2[0], postalHistoryOfItems.get(1).getHistoryRecordId());
        assertEquals(obj2[1], postalHistoryOfItems.get(1).getTimestamp());
        assertEquals(obj2[2], postalHistoryOfItems.get(1).getPostalStatus());
        assertEquals(obj2[3], postalHistoryOfItems.get(1).getPostalOffice().getOfficeTitle());
        assertEquals(obj2[4], postalHistoryOfItems.get(1).getPostalOffice().getOfficeIndex());
        assertEquals(obj2[5], postalHistoryOfItems.get(1).getPostalOffice().getOfficeAddress());
    }

    @Test
    public void testMapToPostalHistoryOfItemNullInput() {

        List<Object[]> result = new ArrayList<>();

        List<PostalHistoryOfItem> postalHistoryOfItems = HistoryConverter.mapToPostalHistoryOfItemDTO(result);

        assertTrue(postalHistoryOfItems.isEmpty());
    }

    @Test
    public void testMapToPostalHistoryOfItemSeveralValues() {

        List<Object[]> result = new ArrayList<>();
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Object[] obj1 = {
                uuid1,
                new Timestamp(System.currentTimeMillis()),
                PostalStatus.IN_OFFICE,
                "Office 1",
                "123456",
                "Address 1"};
        Object[] obj2 = {
                uuid2,
                new Timestamp(System.currentTimeMillis()),
                PostalStatus.OUT_OF_OFFICE,
                "Office 2",
                "654321",
                "Address 2"};
        result.add(obj1);
        result.add(obj2);

        List<PostalHistoryOfItem> postalHistoryOfItems = HistoryConverter.mapToPostalHistoryOfItemDTO(result);

        assertEquals(uuid1, postalHistoryOfItems.get(0).getHistoryRecordId());
        assertEquals(uuid2, postalHistoryOfItems.get(1).getHistoryRecordId());
    }

}
