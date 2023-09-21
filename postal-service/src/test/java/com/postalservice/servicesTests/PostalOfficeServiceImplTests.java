package com.postalservice.servicesTests;

import com.postalservice.entities.PostalOffice;
import com.postalservice.repositories.PostalOfficeRepository;
import com.postalservice.services.PostalOfficeServiceImpl;
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
class PostalOfficeServiceImplTests extends TestData {

    @InjectMocks
    PostalOfficeServiceImpl postalOfficeServiceImpl;

    @Mock
    PostalOfficeRepository postalOfficeRepository;

    private final PostalOffice postalOffice1 = createOffice1();

    @Test
    void createPostalItemTest(){
        when(postalOfficeRepository.save(postalOffice1)).thenReturn(postalOffice1);
        assertEquals(postalOffice1, postalOfficeServiceImpl.createPostalOffice(postalOffice1));
    }

    @Test
    void findByIdTest(){
        when(postalOfficeRepository.findById(postalOffice1.getOfficeIndex()))
                .thenReturn(Optional.of(postalOffice1));
        assertEquals(postalOffice1, postalOfficeServiceImpl.findById(postalOffice1.getOfficeIndex()).orElseThrow());
        verify(postalOfficeRepository, times(1))
                .findById(postalOffice1.getOfficeIndex());
    }

}
