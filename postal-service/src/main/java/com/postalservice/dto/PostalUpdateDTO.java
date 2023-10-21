package com.postalservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class PostalUpdateDTO {

    @Schema(description = "Идентификатор почтового отправления.")
    private UUID postalItemId;

    @Schema(description = "Статус почтового отправления.")
    private String postalStatus;

    @Schema(description = "Время внесения записи.")
    private String timestamp;

}
