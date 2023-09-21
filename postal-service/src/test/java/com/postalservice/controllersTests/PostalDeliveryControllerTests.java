package com.postalservice.controllersTests;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.postalservice.controllers.PostalDeliveryController;
import com.postalservice.dto.PostalHistoryOfItem;
import com.postalservice.dto.PostalItemInfo;

import com.postalservice.entities.PostalHistoryRecord;
import com.postalservice.entities.PostalItem;
import com.postalservice.entities.PostalOffice;
import com.postalservice.enums.PostalStatus;
import com.postalservice.services.PostalHistoryRecordServiceImpl;
import com.postalservice.services.PostalItemServiceImpl;
import com.postalservice.services.PostalOfficeServiceImpl;
import com.postalservice.testData.TestData;

import lombok.SneakyThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostalDeliveryController.class)
class PostalDeliveryControllerTests extends TestData {

    @MockBean
    PostalHistoryRecordServiceImpl postalHistoryRecordServiceImpl;

    @MockBean
    PostalOfficeServiceImpl postalOfficeServiceImpl;

    @MockBean
    PostalItemServiceImpl postalItemServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    private final PostalItem postalItem1 = createItem1();
    private final PostalOffice postalOffice1 = createOffice1();
    private final String postalItemJson1 = createItemJsonString1();
    private final List<PostalHistoryOfItem> postalHistory = createItemHistoryList();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @SneakyThrows
    void registerPostalItem(){

        when(postalItemServiceImpl.createPostalItem(any(PostalItem.class)))
                .thenReturn(postalItem1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postalItemJson1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("\"" + postalItem1.getPostalItemId().toString() + "\""));

        verify(postalItemServiceImpl).createPostalItem(any(PostalItem.class));
        verify(postalHistoryRecordServiceImpl).createPostalHistory(any(PostalHistoryRecord.class));

    }

    @Test
    @SneakyThrows
    void getPostalItemToPostalOffice(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));
        when(postalOfficeServiceImpl.findById(anyString()))
                .thenReturn(Optional.of(postalOffice1));
        when(postalHistoryRecordServiceImpl.getPostalHistory(any(UUID.class)))
                .thenReturn(createItemHistoryList());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/get_to_postal_office/{postal_item_id}&{office_index}",
                                postalItem1.getPostalItemId().toString(), postalOffice1.getOfficeIndex())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("\"" + PostalStatus.IN_OFFICE + "\""));

        verify(postalItemServiceImpl).findById(any(UUID.class));
        verify(postalHistoryRecordServiceImpl).createPostalHistory(any(PostalHistoryRecord.class));
    }

    @Test
    @SneakyThrows
    void sendPostalItemFromPostalOffice(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));
        when(postalOfficeServiceImpl.findById(anyString()))
                .thenReturn(Optional.of(postalOffice1));
        when(postalHistoryRecordServiceImpl.getPostalHistory(any(UUID.class))).
                thenReturn(createItemHistoryList2());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/send_from_postal_office/{postal_item_id}",
                                postalItem1.getPostalItemId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("\"" + PostalStatus.OUT_OF_OFFICE + "\""));

        verify(postalItemServiceImpl).findById(any(UUID.class));
        verify(postalHistoryRecordServiceImpl).createPostalHistory(any(PostalHistoryRecord.class));
    }

    @Test
    @SneakyThrows
    void deliverPostalItemToRecipient(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));
        when(postalHistoryRecordServiceImpl.getPostalHistory(any(UUID.class)))
                .thenReturn(createItemHistoryList());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/deliver_to_recipient/{postal_item_id}",
                                postalItem1.getPostalItemId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("\"" + PostalStatus.RECEIVED + "\""));

        verify(postalItemServiceImpl).findById(any(UUID.class));
        verify(postalHistoryRecordServiceImpl).createPostalHistory(any(PostalHistoryRecord.class));
    }

    @Test
    @SneakyThrows
    void checkPostalItemHistory(){

        PostalItemInfo postalItemInfo = new PostalItemInfo(postalItem1, postalHistory);

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));
        when(postalHistoryRecordServiceImpl.getPostalHistory(any(UUID.class)))
                .thenReturn(postalHistory);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/post_item_delivery/check_history/{postal_item_id}",
                        postalItem1.getPostalItemId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$..postalItem").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$..postalHistoryOfItem").exists())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(postalItemInfo)));

        verify(postalItemServiceImpl).findById(any(UUID.class));
        verify(postalHistoryRecordServiceImpl).getPostalHistory(any(UUID.class));

    }

    @Test
    @SneakyThrows
    void getPostalItemToPostalOfficeThrowsPostalItemAlreadyReceivedException(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));
        when(postalOfficeServiceImpl.findById(anyString()))
                .thenReturn(Optional.of(postalOffice1));
        when(postalHistoryRecordServiceImpl.getPostalHistory(any(UUID.class)))
                .thenReturn(createItemHistoryList4());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/get_to_postal_office/{postal_item_id}&{office_index}",
                                postalItem1.getPostalItemId().toString(), postalOffice1.getOfficeIndex())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(postalItemServiceImpl).findById(any(UUID.class));
    }

    @Test
    @SneakyThrows
    void getPostalItemToPostalOfficeThrowsPreviousOperationIsRequiredException(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));
        when(postalOfficeServiceImpl.findById(anyString()))
                .thenReturn(Optional.of(postalOffice1));
        when(postalHistoryRecordServiceImpl.getPostalHistory(any(UUID.class)))
                .thenReturn(createItemHistoryList2());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/get_to_postal_office/{postal_item_id}&{office_index}",
                                postalItem1.getPostalItemId().toString(), postalOffice1.getOfficeIndex())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(postalItemServiceImpl).findById(any(UUID.class));
    }

    @Test
    @SneakyThrows
    void registerPostalItemThrowsValidationFailedException(){

        BindingResult errors = mock(BindingResult.class);
        when(errors.hasErrors())
                .thenReturn(true);
        RequestBuilder requestBuilder = post("/api/post_item_delivery/register")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"postalType\":\"Письмо\",\"recipientIndex\":\"12321422\",\"recipientAddress\":\"\",\"recipientName\":\"\"}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

        verify(postalItemServiceImpl, never()).findById(any(UUID.class));
        verify(postalItemServiceImpl, never()).createPostalItem(any(PostalItem.class));

    }

    @Test
    @SneakyThrows
    void checkPostalItemHistoryThrowsEntityNotFoundException(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/post_item_delivery/check_history/{postal_item_id}",
                        postalItem1.getPostalItemId().toString()))
                .andExpect(status().isNotFound());

        verify(postalItemServiceImpl, times(1))
                .findById(any(UUID.class));
    }

    @Test
    @SneakyThrows
    void deliverPostalItemToRecipientThrowsEntityNotFoundException(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/deliver_to_recipient/{postal_item_id}",
                                postalItem1.getPostalItemId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(postalItemServiceImpl, times(1))
                .findById(any(UUID.class));
    }

    @Test
    @SneakyThrows
    void sendPostalItemFromPostalOfficeThrowsEntityNotFoundException(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/send_from_postal_office/{postal_item_id}",
                                postalItem1.getPostalItemId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(postalItemServiceImpl, times(1))
                .findById(any(UUID.class));
    }
}
