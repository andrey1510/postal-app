package com.packinglistservice.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@IdClass(PackingElementPK.class)
@Table(name = "packing_element")
public class PackingElement {

    @Id
    @Column(name = "postal_item_id", nullable = false)
    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            description = "Идентификатор почтового отправления.")
    private UUID postalItemId;

    @Id
    @Column(name = "element_number", nullable = false)
    @Schema(example = "1",
            description = "Номер элемента содержимого почтового отправления.")
    private int elementNumber;

    @Column(name = "title", nullable = false)
  //  @NotBlank
    @Schema(minLength = 1, requiredMode = REQUIRED,
            example = "Книга",
            description = "Элемент содержимого почтового отправления.")
    private String title;

    @Column(name = "weight", nullable = false)
  //  @NotBlank
    @Schema(minLength = 1,
            requiredMode = REQUIRED,
            example = "1.1",
            description = "Вес")
    private double weight;

    @Column(name = "price", nullable = false)
  //  @NotBlank
    @Schema(minLength = 1,
            requiredMode = REQUIRED,
            example = "100.20",
            description = "Цена")
    private double price;

}
