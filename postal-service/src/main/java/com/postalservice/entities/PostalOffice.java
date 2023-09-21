package com.postalservice.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "postal_office")
public class PostalOffice {

    @Id
    @Column(name = "office_index", nullable = false)
    @Pattern(regexp = "[0-9]{6}")
    @Schema(pattern = "[0-9]{6}",
            requiredMode = REQUIRED,
            example = "125009",
            description = "Индекс почтового отделения.")
    private String officeIndex;

    @Column(name = "office_title", nullable = false)
    @NotBlank
    @Schema(minLength = 1,
            requiredMode = REQUIRED,
            example = "Почтовое отделение № 125009",
            description = "Название почтового отделения.")
    private String officeTitle;

    @Column(name = "office_address", nullable = false)
    @NotBlank
    @Schema(minLength = 1,
            requiredMode = REQUIRED,
            example = "г. Москва, ул. Тверская, дом 9, стр. 5",
            description = "Адрес почтового отделения.")
    private String officeAddress;

}
