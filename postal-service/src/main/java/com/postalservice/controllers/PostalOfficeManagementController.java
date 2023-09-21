package com.postalservice.controllers;

import com.postalservice.entities.PostalOffice;
import com.postalservice.exceptions.EntityNotFoundException;
import com.postalservice.exceptions.EntityAlreadyExistsException;
import com.postalservice.exceptions.ValidationFailedException;
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

@Validated
@RestController
@RequestMapping("/api/postal_office/")
@RequiredArgsConstructor
public class PostalOfficeManagementController {

    private static final String OFFICE_ALREADY_EXISTS = "Почтовое отделение с таким индексом уже имеется.";
    private static final String OFFICE_NOT_FOUND = "Почтовое отделение с указанным индексом не найдено.";
    private static final String INDEX_WRONG_FORMAT_MESSAGE = "Введенное значение ${validatedValue} не соответствует паттерну {regexp}.";

    private final PostalOfficeService postalOfficeService;

    @PostMapping("register_post_office")
    @Operation(description = "Внести в базу почтовое отделение.")
    public ResponseEntity<PostalOffice> registerPostalOffice(
            @Validated @RequestBody PostalOffice postalOffice, BindingResult errors
    ) {

        if (errors.hasErrors()) throw new ValidationFailedException(errors);

        if (postalOfficeService.findById(postalOffice.getOfficeIndex()).orElse(null) != null) {
            throw new EntityAlreadyExistsException(OFFICE_ALREADY_EXISTS);
        }

        postalOfficeService.createPostalOffice(postalOffice);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{office_index}")
    @Operation(description = "Найти почтовое отделение по индексу.")
    public PostalOffice getPostalOffice(
            @PathVariable("office_index") @Pattern(regexp = "[0-9]{6}", message = INDEX_WRONG_FORMAT_MESSAGE) String officeIndex
    ) {

        return postalOfficeService.findById(officeIndex).orElseThrow(() -> new EntityNotFoundException(OFFICE_NOT_FOUND));

    }

}
