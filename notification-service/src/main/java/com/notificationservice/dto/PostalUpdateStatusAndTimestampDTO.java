package com.notificationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PostalUpdateStatusAndTimestampDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            example = "Доставлено в почтовое отделение.",
            description = "Статус почтового отправления.")
    private String postalStatus;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            example = "2023-10-24T15:46:32.035+00:00",
            description = "Время внесения записи.")
    private Timestamp timestamp;

}
