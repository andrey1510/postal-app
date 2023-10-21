package com.notificationservice.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "postal_update")
public class PostalUpdate {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            description = "Идентификатор записи обновления.")
    private UUID id;

    @Column(name = "postal_item_id", updatable = false, nullable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            description = "Идентификатор почтового отправления.")
    private UUID postalItemId;

    @Column(name = "postal_status", updatable = false, nullable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            example = "Доставлено в почтовое отделение.",
            description = "Статус почтового отправления.")
    private String postalStatus;

    @Column(name = "timestamp", updatable = false, nullable = false)
    private String timestamp;


}
