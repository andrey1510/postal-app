package com.postalservice.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "postal_subscription")
public class PostalSubscription {

    @Id
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(unique = true, nullable=false)
    private PostalItem postalItem;

    @Column(name = "subscription_status", nullable = false)
    private boolean subscriptionStatus;

}
