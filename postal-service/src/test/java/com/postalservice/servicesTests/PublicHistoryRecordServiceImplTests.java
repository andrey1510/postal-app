package com.postalservice.servicesTests;

import com.postalservice.dto.PostalHistoryOfItem;
import com.postalservice.entities.PostalHistoryRecord;
import com.postalservice.entities.PostalItem;
import com.postalservice.repositories.PostalHistoryRecordRepository;
import com.postalservice.services.PostalHistoryRecordServiceImpl;
import com.postalservice.testData.TestData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublicHistoryRecordServiceImplTests extends TestData {

    @InjectMocks
    PostalHistoryRecordServiceImpl postalHistoryRecordServiceImpl;

    @Mock
    PostalHistoryRecordRepository postalHistoryRecordRepository;

    private final PostalHistoryRecord postalHistoryRecord1 = createHistoryRecord1();
    private final PostalHistoryRecord postalHistoryRecord2 = createHistoryRecord2();
    private final PostalItem postalItem1 = createItem1();
    private final List<Object[]> objectsList = createItemObjects();
    private final List<PostalHistoryOfItem> itemList = createItemHistoryList3();

    @Test
    void createPostalHistoryTest() {

        when(postalHistoryRecordRepository.save(postalHistoryRecord1))
                .thenReturn(postalHistoryRecord1);
        assertEquals(postalHistoryRecord1, postalHistoryRecordServiceImpl.createPostalHistory(postalHistoryRecord1));
        verify(postalHistoryRecordRepository, times(1))
                .save(postalHistoryRecord1);

        when(postalHistoryRecordRepository.save(postalHistoryRecord2))
                .thenReturn(postalHistoryRecord2);
        assertEquals(postalHistoryRecord2, postalHistoryRecordServiceImpl.createPostalHistory(postalHistoryRecord2));
        verify(postalHistoryRecordRepository, times(1))
                .save(postalHistoryRecord2);
    }

    @Test
    void getPostalHistoryTest(){
        when(postalHistoryRecordRepository.getPostalHistory(postalItem1.getPostalItemId()))
                .thenReturn(objectsList);

        assertEquals(itemList, postalHistoryRecordServiceImpl.getPostalHistory(postalItem1.getPostalItemId()));
        verify(postalHistoryRecordRepository, times(1))
                .getPostalHistory(postalItem1.getPostalItemId());
    }

}