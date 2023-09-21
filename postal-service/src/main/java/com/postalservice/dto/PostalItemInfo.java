package com.postalservice.dto;

import com.postalservice.entities.PostalItem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PostalItemInfo {

    @Schema(description = "Информация о почтовом отправлении.")
    private PostalItem postalItem;

    @Schema(description = "История почтового отправления.")
    private List<PostalHistoryOfItem> postalHistoryOfItem;

}
