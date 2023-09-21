package com.postalservice.repositoriesTests;

import com.postalservice.entities.PostalHistoryRecord;
import com.postalservice.repositories.PostalHistoryRecordRepository;
import com.postalservice.repositories.PostalItemRepository;
import com.postalservice.testData.TestData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class PostalHistoryRecordRepositoryTests extends TestData {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostalHistoryRecordRepository postalHistoryRecordRepository;

    @Autowired
    private PostalItemRepository postalItemRepository;

    private final PostalHistoryRecord postalHistoryRecordForDB2 = createHistoryRecordForDB2();

    @Test
    @Rollback
    void testGetPostalHistory() {

        entityManager.persist(postalHistoryRecordForDB2);

        List<Object[]> postalHistory = postalHistoryRecordRepository.getPostalHistory(postalItemRepository
                .findAll()
                .get(0)
                .getPostalItemId());
        Object[] record1 = postalHistory.get(0);

        Assertions.assertEquals(1, postalHistory.size());
        Assertions.assertEquals(6, record1.length);
        Assertions.assertEquals(postalHistoryRecordForDB2.getTimestamp(), record1[1]);
        Assertions.assertEquals(postalHistoryRecordForDB2.getPostalStatus(), record1[2]);
        Assertions.assertEquals(postalHistoryRecordForDB2.getPostalOffice().getOfficeIndex(), record1[4]);

    }

}