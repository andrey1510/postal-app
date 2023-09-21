package com.postalservice.controllersTests;

import com.postalservice.controllers.PostalItemManagementController;
import com.postalservice.entities.PostalItem;
import com.postalservice.services.PostalItemServiceImpl;
import com.postalservice.testData.TestData;

import lombok.SneakyThrows;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostalItemManagementController.class)
class PostalItemManagementControllerTests extends TestData {

    @MockBean
    PostalItemServiceImpl postalItemServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    private final PostalItem postalItem1 = createItem1();

    @Test
    @SneakyThrows
    void findById() {

        when(postalItemServiceImpl.findById(postalItem1.getPostalItemId()))
                .thenReturn(Optional.of(postalItem1));

        this.mockMvc.perform(get("/api/postal_item/{itemUUID1}", postalItem1.getPostalItemId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.postalItemId", Matchers.is(postalItem1.getPostalItemId().toString())))
                .andExpect(jsonPath("$.postalType", Matchers.is(postalItem1.getPostalType().toString())))
                .andExpect(jsonPath("$.recipientIndex", Matchers.is(postalItem1.getRecipientIndex())))
                .andExpect(jsonPath("$.recipientAddress", Matchers.is(postalItem1.getRecipientAddress())))
                .andExpect(jsonPath("$.recipientName", Matchers.is(postalItem1.getRecipientName())));
    }

    @Test
    @SneakyThrows
    void findByIdThrowsEntityNotFoundException() {

        UUID wrongUUID = UUID.fromString("d1394e33-c225-49e5-a8f0-d2d8052b7af8");
        when(postalItemServiceImpl.findById(wrongUUID))
                .thenReturn(Optional.empty());

        this.mockMvc.perform(get("/api/postal_item/{itemUUID1}", postalItem1.getPostalItemId().toString()))
                .andExpect(status().isNotFound());
    }

}
