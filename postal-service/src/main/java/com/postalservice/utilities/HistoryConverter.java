package com.postalservice.utilities;

import com.postalservice.dto.PostalHistoryOfItem;
import com.postalservice.entities.PostalOffice;
import com.postalservice.enums.PostalStatus;

import java.sql.Timestamp;

import java.util.List;
import java.util.UUID;

public class HistoryConverter {

    private HistoryConverter() {
    }

    public static List<PostalHistoryOfItem> mapToPostalHistoryOfItemDTO(List<Object[]> result) {
        return result.stream().map(e -> {

            PostalHistoryOfItem postalHistoryOfItem = new PostalHistoryOfItem();
            postalHistoryOfItem.setHistoryRecordId((UUID) e[0]);
            postalHistoryOfItem.setTimestamp((Timestamp) e[1]);
            postalHistoryOfItem.setPostalStatus((PostalStatus) e[2]);

            PostalOffice postalOffice = new PostalOffice();
            postalOffice.setOfficeTitle((String) e[3]);
            postalOffice.setOfficeIndex((String) e[4]);
            postalOffice.setOfficeAddress((String) e[5]);

            postalHistoryOfItem.setPostalOffice(postalOffice);

            return postalHistoryOfItem;

        }).toList();

    }

}
