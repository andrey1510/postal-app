package com.postalservice.controllers;

import com.postalservice.exceptions.PostalItemAlreadyReceivedException;
import com.postalservice.exceptions.EntityNotFoundException;
import com.postalservice.exceptions.EntityAlreadyExistsException;
import com.postalservice.exceptions.PreviousOperationIsRequiredException;
import com.postalservice.exceptions.ValidationFailedException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.regex.Pattern;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllersExceptionsHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleGeneralException(Exception ex) {
        return new ErrorResponse("General exception: " + ex.getMessage());
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleRecordAlreadyExistsException(EntityAlreadyExistsException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleIdNotFoundException(EntityNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleValidationFailedException(ValidationFailedException ex) {
         return new ErrorResponse(ex.getMessage());
   }

    @ExceptionHandler(PostalItemAlreadyReceivedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePostalItemAlreadyReceivedException(PostalItemAlreadyReceivedException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(PreviousOperationIsRequiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePreviousOperationIsRequiredException(PreviousOperationIsRequiredException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleFieldValidationFailedException(ConstraintViolationException ex) {
        return new ErrorResponse(Pattern.compile(".*: ")
                .matcher(ex.getMessage())
                .replaceAll(""));
    }

    @AllArgsConstructor
    @Getter
    private static class ErrorResponse {
        @Schema(description = "Сообщение об ошибке.", example = "Почтовое отделение с таким индексом уже имеется.")
        private final String message;
    }

}

