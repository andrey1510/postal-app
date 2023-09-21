package com.postalservice.servicesTests;

import com.postalservice.entities.PostalItem;
import com.postalservice.repositories.PostalItemRepository;
import com.postalservice.services.PostalItemServiceImpl;
import com.postalservice.testData.TestData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PostalItemServiceImplTests extends TestData {

    @InjectMocks
    PostalItemServiceImpl postalItemServiceImpl;

    @Mock
    PostalItemRepository postalItemRepository;

    private final PostalItem postalItem1 = createItem1();

    @Test
    void createPostalItemTest(){
        when(postalItemRepository.save(postalItem1)).thenReturn(postalItem1);
        assertEquals(postalItem1, postalItemServiceImpl.createPostalItem(postalItem1));
    }

    @Test
    void findByIdTest(){
        when(postalItemRepository.findById(postalItem1.getPostalItemId()))
                .thenReturn(Optional.of(postalItem1));
        assertEquals(postalItem1, postalItemServiceImpl.findById(postalItem1.getPostalItemId()).orElseThrow());
        verify(postalItemRepository, times(1))
                .findById(postalItem1.getPostalItemId());
    }

}
