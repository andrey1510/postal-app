package com.postalservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.postalservice.entities.PostalOffice;
import com.postalservice.enums.PostalStatus;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PostalHistoryOfItem {

    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", description = "ID записи в истории.")
    private UUID historyRecordId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss a z")
    @Schema(example = "2023-09-08T13:31:42 PM UTC", description = "Время регистрации записи в истории.")
    private Timestamp timestamp;

    @Schema(description = "Статус почтового отправления.")
    private PostalStatus postalStatus;

    @Schema(description = "Почтовое отделение.")
    private PostalOffice postalOffice;


}
