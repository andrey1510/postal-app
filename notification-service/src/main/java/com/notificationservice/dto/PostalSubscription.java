package com.notificationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PostalSubscription {

    @Schema(description = "Идентификатор почтового отправления.")
    private UUID postalItemId;

    @Schema(description = "Включение/выключение обновлений.")
    private boolean subscriptionStatus;

}
