package com.postalservice.controllers;

import com.postalservice.dto.PostalHistoryOfItem;
import com.postalservice.dto.PostalItemInfo;
import com.postalservice.entities.PostalHistoryRecord;
import com.postalservice.entities.PostalItem;
import com.postalservice.entities.PostalOffice;
import com.postalservice.enums.PostalStatus;
import com.postalservice.exceptions.PostalItemAlreadyReceivedException;
import com.postalservice.exceptions.EntityNotFoundException;
import com.postalservice.exceptions.PreviousOperationIsRequiredException;
import com.postalservice.exceptions.ValidationFailedException;
import com.postalservice.services.PostalItemService;
import com.postalservice.services.PostalHistoryRecordService;
import com.postalservice.services.PostalOfficeService;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/post_item_delivery/")
@RequiredArgsConstructor
public class PostalDeliveryController {

    private static final String ITEM_NOT_FOUND = "Почтовое отправление с указанным идентификатором не найдено.";
    private static final String OFFICE_NOT_FOUND = "Почтовое отделение с указанным индексом не найдено.";
    private static final String INDEX_WRONG_FORMAT_MESSAGE = "Введенное значение ${validatedValue} не соответствует паттерну {regexp}";
    private static final String ITEM_ALREADY_RECEIVED = "Внесение данной записи невозможно, т.к. почтовое отправление уже было доставлено адресату.";
    private static final String PREVIOUS_OPERATION_IS_GET = "Внесение данной записи невозможно, т.к. предыдущая запись была записью о прибытии в отделение.";
    private static final String PREVIOUS_OPERATION_IS_SEND = "Внесение данной записи невозможно, т.к. предыдущая запись должна быть записью о прибытии отправления в почтовое отделение.";

    private final PostalItemService postalItemService;
    private final PostalHistoryRecordService postalHistoryRecordService;
    private final PostalOfficeService postalOfficeService;

    @PostMapping("register")
    @Operation(description = "Зарегистрировать почтовое отправление.")
    public ResponseEntity<UUID> registerPostalItem(@Validated @RequestBody PostalItem postalItem, BindingResult errors) {

        if (errors.hasErrors()) throw new ValidationFailedException(errors);

        PostalItem createdItem = postalItemService.createPostalItem(postalItem);
        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(PostalStatus.REGISTERED, createdItem);

        postalHistoryRecordService.createPostalHistory(postalHistoryRecord);

        return new ResponseEntity<>(createdItem.getPostalItemId(), HttpStatus.OK);
    }

    @PostMapping("get_to_postal_office/{postal_item_id}&{office_index}")
    @Operation(description = "Внести запись о прибытии почтового отправления в почтовое отделение")
    public ResponseEntity<PostalStatus> getPostalItemToPostalOffice(
            @PathVariable("postal_item_id") UUID postalItemId,
            @PathVariable("office_index") @Pattern(regexp = "[0-9]{6}", message = INDEX_WRONG_FORMAT_MESSAGE) String officeIndex
    ) {

        PostalItem postalItem = postalItemService.findById(postalItemId)
                .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND));
        PostalOffice postalOffice = postalOfficeService.findById(officeIndex)
                .orElseThrow(() -> new EntityNotFoundException(OFFICE_NOT_FOUND));

        PostalStatus lastStatus = postalHistoryRecordService.getPostalHistory(postalItemId).get(0).getPostalStatus();
        if (lastStatus == PostalStatus.RECEIVED) {
            throw new PostalItemAlreadyReceivedException(ITEM_ALREADY_RECEIVED);
        } else if (lastStatus == PostalStatus.IN_OFFICE) {
            throw new PreviousOperationIsRequiredException(PREVIOUS_OPERATION_IS_GET);
        }

        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(PostalStatus.IN_OFFICE, postalItem, postalOffice);

        postalHistoryRecordService.createPostalHistory(postalHistoryRecord);

        return new ResponseEntity<>(postalHistoryRecord.getPostalStatus(), HttpStatus.OK);
    }

    @PostMapping("send_from_postal_office/{postal_item_id}")
    @Operation(description = "Внести запись об убытии почтового отправления из почтового отделения.")
    public ResponseEntity<PostalStatus> sendPostalItemFromPostalOffice(@PathVariable("postal_item_id") UUID postalItemId) {

        PostalItem postalItem = postalItemService.findById(postalItemId)
                .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND));

        PostalStatus lastStatus = postalHistoryRecordService.getPostalHistory(postalItemId).get(0).getPostalStatus();
        if (lastStatus != PostalStatus.IN_OFFICE)
            throw new PreviousOperationIsRequiredException(PREVIOUS_OPERATION_IS_SEND);

        String lastIndex = postalHistoryRecordService.getPostalHistory(postalItemId).get(0).getPostalOffice().getOfficeIndex();
        PostalOffice postalOffice = postalOfficeService.findById(lastIndex).orElseThrow();

        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(PostalStatus.OUT_OF_OFFICE, postalItem, postalOffice);

        postalHistoryRecordService.createPostalHistory(postalHistoryRecord);

        return new ResponseEntity<>(postalHistoryRecord.getPostalStatus(), HttpStatus.OK);
    }

    @PostMapping("deliver_to_recipient/{postal_item_id}")
    @Operation(description = "Внести запись о получении почтового отправления адресатом.")
    public ResponseEntity<PostalStatus> deliverPostalItemToRecipient(@PathVariable("postal_item_id") UUID postalItemId){

        PostalItem postalItem = postalItemService.findById(postalItemId)
                .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND));

        if (PostalStatus.RECEIVED == postalHistoryRecordService.getPostalHistory(postalItemId).get(0).getPostalStatus())
            throw new PostalItemAlreadyReceivedException(ITEM_ALREADY_RECEIVED);

        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(PostalStatus.RECEIVED, postalItem);

        postalHistoryRecordService.createPostalHistory(postalHistoryRecord);

        return new ResponseEntity<>(postalHistoryRecord.getPostalStatus(), HttpStatus.OK);
    }

    @GetMapping("check_history/{postal_item_id}")
    @Operation(description = "Просмотреть статус и полную историю движения почтового отправления " +
            "(сортировка от более позднего к более раннему).")
    public PostalItemInfo checkPostalItemHistory(@PathVariable("postal_item_id") UUID postalItemId) {

        PostalItem postalItem = postalItemService.findById(postalItemId)
                .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND));
        List<PostalHistoryOfItem> postalHistory = postalHistoryRecordService.getPostalHistory(postalItemId);

        return new PostalItemInfo(postalItem, postalHistory);
    }

}
