package com.postalservice.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@AllArgsConstructor
public class ValidationFailedException extends RuntimeException {

    private final BindingResult errors;

    @Override
    public String getMessage() {
        return this.errors.getAllErrors()
                .stream()
                .map(ValidationFailedException::getValidationMessage)
                .collect(Collectors.joining("; "));
    }

    private static String getValidationMessage(ObjectError error) {

        if (error instanceof FieldError fieldError) {
            String property = fieldError.getField();
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            return String.format("Введенное значение %s не соответствует следующему условию: %s %s",
                    invalidValue, property, message);
        }

        return String.format("%s: %s", error.getObjectName(), error.getDefaultMessage());
    }

}
