package com.postalservice.testData;

import com.postalservice.dto.PostalHistoryOfItem;
import com.postalservice.entities.PostalHistoryRecord;
import com.postalservice.entities.PostalItem;
import com.postalservice.entities.PostalOffice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.postalservice.enums.PostalStatus.IN_OFFICE;
import static com.postalservice.enums.PostalStatus.OUT_OF_OFFICE;
import static com.postalservice.enums.PostalStatus.RECEIVED;
import static com.postalservice.enums.PostalStatus.REGISTERED;
import static com.postalservice.enums.PostalType.LETTER;

public abstract class TestData {

    protected PostalOffice createOffice1(){
        return PostalOffice.builder()
                .officeIndex("223005")
                .officeAddress("г. Ярославль, ул. Северная, дом 3")
                .officeTitle("Второе почтовое отделение")
                .build();
    }

    protected PostalOffice createOfficeNull(){
        return PostalOffice.builder()
                .officeIndex(null)
                .officeAddress(null)
                .officeTitle(null)
                .build();
    }

    protected PostalItem createItem1(){
        return PostalItem.builder()
                .postalItemId(UUID.fromString("4165272a-e9e9-40f6-b1c5-29ca31f0d1e2"))
                .postalType(LETTER)
                .recipientIndex("101001")
                .recipientAddress("г. Москва, ул. Первая, дом 1")
                .recipientName("Петров Петр Петрович")
                .build();
    }

    protected PostalItem createItemForDB(){
        return PostalItem.builder()
                .postalType(LETTER)
                .recipientIndex("101001")
                .recipientAddress("г. Москва, ул. Первая, дом 1")
                .recipientName("Петров Петр Петрович")
                .build();
    }

    protected PostalHistoryRecord createHistoryRecord1(){
        return PostalHistoryRecord.builder()
                .historyRecordId(UUID.fromString("a9c73dd6-78bb-48ae-beae-ff4c1136793e"))
                .postalStatus(REGISTERED)
                .postalItem(createItem1())
                .postalOffice(createOfficeNull())
                .timestamp(Timestamp.valueOf("2023-08-26 19:19:38.11161"))
                .build();
    }

    protected PostalHistoryRecord createHistoryRecord2(){
        return PostalHistoryRecord.builder()
                .historyRecordId(UUID.fromString("d1394e33-c225-49e5-a8f0-d2d8052b7af8"))
                .postalStatus(IN_OFFICE)
                .postalItem(createItem1())
                .postalOffice(createOffice1())
                .timestamp(Timestamp.valueOf("2023-08-27 12:29:38.22861"))
                .build();
    }

    protected PostalHistoryRecord createHistoryRecordForDB1(){
        return PostalHistoryRecord.builder()
                .postalStatus(REGISTERED)
                .postalItem(createItemForDB())
                .postalOffice(createOfficeNull())
                .timestamp(Timestamp.valueOf("2023-08-26 19:19:38.11161"))
                .build();
    }

    protected PostalHistoryRecord createHistoryRecordForDB2(){
        return PostalHistoryRecord.builder()
                .postalStatus(IN_OFFICE)
                .postalItem(createItemForDB())
                .postalOffice(createOffice1())
                .timestamp(Timestamp.valueOf("2023-08-27 12:29:38.22861"))
                .build();
    }

    protected List<Object[]> createItemObjects(){
        return new ArrayList<>(List.of(
                new Object[] {
                        UUID.fromString("a9c73dd6-78bb-48ae-beae-ff4c1136793e"),
                        Timestamp.valueOf("2023-08-26 19:19:38.11161"),
                        REGISTERED,
                        null,
                        null,
                        null
                },
                new Object[] {
                        UUID.fromString("d1394e33-c225-49e5-a8f0-d2d8052b7af8"),
                        Timestamp.valueOf("2023-08-27 12:29:38.22861"),
                        IN_OFFICE,
                        "Второе почтовое отделение",
                        "223005",
                        "г. Ярославль, ул. Северная, дом 3"
                }

        ));
    }

    protected List<PostalHistoryOfItem> createItemHistoryList3(){

        return new ArrayList<>(List.of(
                PostalHistoryOfItem.builder()
                        .historyRecordId(UUID.fromString("a9c73dd6-78bb-48ae-beae-ff4c1136793e"))
                        .timestamp(Timestamp.valueOf("2023-08-26 19:19:38.11161"))
                        .postalStatus(REGISTERED)
                        .postalOffice(createOfficeNull())
                        .build(),
                PostalHistoryOfItem.builder()
                        .historyRecordId(UUID.fromString("d1394e33-c225-49e5-a8f0-d2d8052b7af8"))
                        .timestamp(Timestamp.valueOf("2023-08-27 12:29:38.22861"))
                        .postalStatus(IN_OFFICE)
                        .postalOffice(createOffice1())
                        .build()
        ));
    }

    protected List<PostalHistoryOfItem> createItemHistoryList(){
        return new ArrayList<>(List.of(
                PostalHistoryOfItem.builder()
                        .historyRecordId(UUID.fromString("a9c73dd6-78bb-48ae-beae-ff4c1136793e"))
                        .timestamp(Timestamp.valueOf("2023-08-27 12:29:38.22861"))
                        .postalStatus(OUT_OF_OFFICE)
                        .postalOffice(createOffice1())
                        .build(),
                PostalHistoryOfItem.builder()
                        .historyRecordId(UUID.fromString("d1394e33-c225-49e5-a8f0-d2d8052b7af8"))
                        .timestamp(Timestamp.valueOf("2023-08-26 19:19:38.11161"))
                        .postalStatus(REGISTERED)
                        .postalOffice(createOfficeNull())
                        .build()
        ));
    }

    protected List<PostalHistoryOfItem> createItemHistoryList2(){
        return new ArrayList<>(List.of(
                PostalHistoryOfItem.builder()
                        .historyRecordId(UUID.fromString("a9c73dd6-78bb-48ae-beae-ff4c1136793e"))
                        .timestamp(Timestamp.valueOf("2023-08-30 12:29:38.22861"))
                        .postalStatus(IN_OFFICE)
                        .postalOffice(createOffice1())
                        .build(),
                PostalHistoryOfItem.builder()
                        .historyRecordId(UUID.fromString("d1394e33-c225-49e5-a8f0-d2d8052b7af8"))
                        .timestamp(Timestamp.valueOf("2023-08-26 19:19:38.11161"))
                        .postalStatus(REGISTERED)
                        .postalOffice(createOfficeNull())
                        .build()
        ));
    }

    protected List<PostalHistoryOfItem> createItemHistoryList4(){
        return new ArrayList<>(List.of(
                PostalHistoryOfItem.builder()
                        .historyRecordId(UUID.fromString("a9c73dd6-78bb-48ae-beae-ff4c1136793e"))
                        .timestamp(Timestamp.valueOf("2023-08-30 12:29:38.22861"))
                        .postalStatus(RECEIVED)
                        .postalOffice(createOffice1())
                        .build(),
                PostalHistoryOfItem.builder()
                        .historyRecordId(UUID.fromString("d1394e33-c225-49e5-a8f0-d2d8052b7af8"))
                        .timestamp(Timestamp.valueOf("2023-08-26 19:19:38.11161"))
                        .postalStatus(REGISTERED)
                        .postalOffice(createOfficeNull())
                        .build()
        ));
    }

    protected String createItemJsonString1(){
        return "{" +
                "\"postalType\":\"Письмо\"," +
                "\"recipientIndex\":\"101001\"," +
                "\"recipientAddress\":\"г. Москва, ул. Первая, дом 1\"," +
                "\"recipientName\":\"Петров Петр Петрович\"}";
    }

}
