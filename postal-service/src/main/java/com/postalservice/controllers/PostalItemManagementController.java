package com.postalservice.controllers;

import com.postalservice.entities.PostalItem;
import com.postalservice.exceptions.EntityNotFoundException;
import com.postalservice.services.PostalItemService;

import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/postal_item/")
@RequiredArgsConstructor
public class PostalItemManagementController {

    private static final String ITEM_NOT_FOUND = "Почтовое отправление с указанным идентификатором не найдено.";

    private final PostalItemService postalItemService;

    @GetMapping("{postal_item_id}")
    @Operation(description = "Найти почтовое отправление по идентификатору.")
    public PostalItem findById (@PathVariable("postal_item_id") UUID postalItemId) {
        return postalItemService.findById(postalItemId)
                .orElseThrow(() -> new EntityNotFoundException(ITEM_NOT_FOUND));
    }

}
