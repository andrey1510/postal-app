package com.postalservice.entities;

import com.postalservice.enums.PostalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "postal_history_record")
public class PostalHistoryRecord {

    @Id
    @GeneratedValue
    @Column(name = "history_record_id", updatable = false, nullable = false)
    @Schema(requiredMode = REQUIRED, description = "ID записи в истории.")
    private UUID historyRecordId;

    @Column(name = "postal_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(requiredMode = REQUIRED, description = "Статус почтового отправления.")
    private PostalStatus postalStatus;

    @ManyToOne(cascade= CascadeType.ALL)
    private PostalItem postalItem;

    @ManyToOne(cascade=CascadeType.ALL)
    private PostalOffice postalOffice;

    @Column(name = "timestamp", updatable = false, nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;

    public PostalHistoryRecord(PostalStatus postalStatus, PostalItem postalItem) {
        this.postalStatus = postalStatus;
        this.postalItem = postalItem;
    }

    public PostalHistoryRecord(PostalStatus postalStatus, PostalItem postalItem, PostalOffice postalOffice) {
        this.postalStatus = postalStatus;
        this.postalItem = postalItem;
        this.postalOffice = postalOffice;
    }

}
